# springbootall
springboot all demo and integration, including some process engine.


[feature/elasticsearch_high_rest_client] 1/9

1. including high rest client dependency in pom,
2. add high rest client configuration in config,
3. add ES CURD service for add document and aggregate query in elasticsearch.

for validate: 

curl -XGET 'http://127.0.0.1:9200/users/_doc/_search?pretty=true'

