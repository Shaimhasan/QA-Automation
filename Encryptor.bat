@ECHO OFF
title Run Encryptor
set fileSeparator=\
set envDirectory=%~dp0src%fileSeparator%test%fileSeparator%resources%fileSeparator%config%fileSeparator%environments%fileSeparator%
java -cp %CD%\lib\runner\Encryptor.jar;..\lib\* com.naqe.Encryptor %envDirectory%
PAUSE