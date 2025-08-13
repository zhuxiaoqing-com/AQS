rd /s /q  "../java/com/pb/"
md "../java/com/pb/"
protoc.exe ./*.proto --java_out=../java/
pause