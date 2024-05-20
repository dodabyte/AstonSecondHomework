start %ELASTIC_SEARCH_HOME%\bin\elasticsearch.bat
timeout 20
start %KIBANA_HOME%\bin\kibana.bat
timeout 20
start %LOGSTASH_HOME%\bin\logstash.bat -f %LOGSTASH_HOME%\config\