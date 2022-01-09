# springbootall
springboot all demo and integration, including some process engine.


## Special warning:

springboot 2.6.x need elasticsearchTemplate.
We only integrated repository type to read es.
In other words, either down grade the version of springboot parent, 
or use elasticsearchTemplate instead of ES repository.

## feature:

elasticsearch data starter repository way to connect elasticsearch.

Now this type is deprecated, high rest client is recommended by official site.

@see branch feature/elasticsearch_high_rest_client

