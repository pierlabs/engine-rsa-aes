
package criptografia;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class GerenciadorRSA {

     private KeyFactory keyFactory = null;

     private Cipher cipher;
     
     public GerenciadorRSA() {

          try {

               keyFactory = KeyFactory.getInstance("RSA");
               
               cipher = Cipher.getInstance("RSA");

          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public PrivateKey carregaChavePrivada(String chaveSSHKeyGenPrivada) {

          String txt = chaveSSHKeyGenPrivada.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");

          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(txt));

          PrivateKey pk = null;

          try {
               pk = keyFactory.generatePrivate(keySpec);
          } catch (Exception e) {
               e.printStackTrace();
          }

          return pk;
     }

     public PublicKey carregaChavePublica(String chaveSSHKeyGenPublic) {

          String txt = chaveSSHKeyGenPublic.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

          X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(txt));

          PublicKey pk = null;

          try {
               
               pk = keyFactory.generatePublic(keySpecX509);
               
          } catch (Exception e) {
               e.printStackTrace();
          }

          return pk;

     }
     
     public byte[] descriptografar(byte[] informacaoCifrada, PrivateKey privateKey) {

          try {

               cipher.init(Cipher.DECRYPT_MODE, privateKey);

               return cipher.doFinal(informacaoCifrada);

          } catch (InvalidKeyException e) {

               System.out.println("Chave privada (RSA) inválida.");

          } catch (Exception e) {

               System.out.println("Erro ao tentar descriptografar com a chave privada (RSA).");

          }

          return null;

     }
     
     public byte[] criptografar(byte[] informacao, PublicKey publicKey) {

          try {

               // inicializa o algoritmo para a criptografia
               cipher.init(Cipher.ENCRYPT_MODE, publicKey);

               return cipher.doFinal(informacao);

          } catch (InvalidKeyException e) {

               System.out.println("Chave pública (RSA) inválida.");

          } catch (Exception e) {

               System.out.println("Erro ao tentar Criptografar com a chave pública (RSA): " + e);

          }

          return null;

     }
}
