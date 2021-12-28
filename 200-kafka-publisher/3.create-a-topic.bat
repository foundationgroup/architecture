cd D:\kafka_2.12-2.8.1\bin\windows
CALL kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic myTopic

PAUSE