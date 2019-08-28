package com.lighthouse.resultautomation.controller;

import com.lighthouse.resultautomation.gqlDatafetcher.AllUserDataFetcher;
import com.lighthouse.resultautomation.gqlDatafetcher.UserDataFetcher;
import org.springframework.web.bind.annotation.RestController;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import graphql.ExecutionResult;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@RestController
public class GraphQLController {
    private final Logger LOGGER = LoggerFactory.getLogger(GraphQLController.class);

    @Value("classpath:/graphql/user.graphql")
    private Resource resource;
    private GraphQL graphQL;

    @Autowired
    private AllUserDataFetcher allUserDataFetcher;
    @Autowired
    private UserDataFetcher getUser;

    @PostConstruct
    public void loadSchema() throws IOException{
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);     // parse schema
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("users", allUserDataFetcher)
                        .dataFetcher("user", getUser) )
                .build();
    }

    @RequestMapping(value = "/graphql-users", method = RequestMethod.POST)
    public ResponseEntity<Object> getGraphQlUsers(@RequestBody String userQuery){
        ExecutionResult result = graphQL.execute(userQuery);
        LOGGER.info(String.valueOf(result.getErrors()));
        return ResponseEntity.ok(result.getData());
    }
}
