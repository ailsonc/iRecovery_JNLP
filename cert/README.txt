1. No diret�rio onde est�o os jars, digite a linha de comando(CMD) que segue:

keytool -genkey -v -keystore c:\desenv\irecovery.cert -alias irecovery -keyalg RSA -keysize 2048 -validity 3660

   	Informe a senha da �rea de armazenamento de chaves: 123456
	Informe novamente a nova senha: 123456
	Qual � o seu nome e o seu sobrenome?
  		[Unknown]:  Costa
	Qual � o nome da sua unidade organizacional?
  		[Unknown]:  Costa
	Qual � o nome da sua empresa?
  		[Unknown]:  Costa
	Qual � o nome da sua Cidade ou Localidade?
  		[Unknown]:  Salvador
	Qual � o nome do seu Estado ou Munic�pio?
 		[Unknown]:  Bahia
	Quais s�o as duas letras do c�digo do pa�s desta unidade?
  		[Unknown]:  Brasil
	(RETURN se for igual � senha da �rea do armazenamento de chaves): 123456
	Informe novamente a nova senha: 123456
	
	Gerando o par de chaves RSA de 2.048 bit e o certificado autoassinado (SHA256withRSA) com uma validade de 3.660 dias

2. No diret�rio onde est�o os jars, digite a linha de comando(CMD) que segue:

jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\commons-cli-1.2.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\commons-codec-1.11.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\commons-logging-1.2.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\fluent-hc-4.5.10.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpclient-4.5.10.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpclient-cache-4.5.10.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpclient-osgi-4.5.10.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpclient-win-4.5.10.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpcore-4.4.12.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpcore-ab-4.4.12.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpcore-nio-4.4.12.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpcore-osgi-4.4.12.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\httpmime-4.5.10.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\jna-4.5.2.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\jna-platform-4.5.2.jar irecovery
jarsigner -verbose -keystore c:\desenv\irecovery.cert c:\desenv\java\iRecovery\lib\json-20190722.jar irecovery


jarsigner -verbose -keystore irecovery.cert (seu arquivo jar).jar irecovery 