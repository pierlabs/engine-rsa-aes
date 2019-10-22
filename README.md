#### Geração do par de chaves usando OPENSSL
* **1ª Gera a chave PRIVADA** 

    *openssl genrsa -out chave_rsa.pem 2048*
    
* **2ª Gera a chave PÚBLICA a partir da privada**

    openssl rsa -pubout -in chave_rsa.pem -out chave_publica.key
    
* **3ª Gerar chave BEGIN PRIVATE KEY para usar no projeto**

    openssl pkcs8 -topk8 -inform PEM -in chave_rsa.pem -out chave_privada.pem -nocrypt
