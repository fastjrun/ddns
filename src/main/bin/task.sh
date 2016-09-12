cd "$(dirname "$0")"

lib=./lib
classpath=.

for file in ${lib}/*.jar; 
    do classpath=${classpath}:$file; 
done

$JAVA_HOME/bin/java -Xms${jvm.ms} -Xmx${jvm.mx} -Dfile.encoding=utf-8 -classpath ${classpath} com.fastjrun.ddns.task.CheckIPTask