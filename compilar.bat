@echo off
echo Compilando proyecto Ahorcado...
if not exist build\classes mkdir build\classes
javac -d build\classes src\ahorcadosprueba\*.java
echo.
echo Compilacion completada!
echo Los archivos .class estan en: build\classes\ahorcadosprueba\
pause

