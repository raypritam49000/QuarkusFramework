package com.rest.api.services;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.rest.api.models.Person;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class PersonServiceImpl implements PersonService {

    @Inject
    private MongoClient mongoClient;

    @Override
    public Boolean addPerson(Person person) {
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("person").getCollection("person");

        Document document = new Document();
        document.append("name", person.getName());
        document.append("city", person.getCity());
        document.append("salary", person.getSalary());

        mongoCollection.insertOne(document);
        return true;
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("person").getCollection("person");
        FindIterable<Document> findIterable = mongoCollection.find();
        MongoCursor<Document> cursor = findIterable.iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Person person = new Person();
                person.setName(document.getString("name"));
                person.setSalary(document.getString("salary"));
                person.setCity(document.getString("city"));
                person.setId(document.getObjectId("_id").toString());
                list.add(person);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public Person getPerson(String id) {
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("person").getCollection("person");
        ObjectId objectId = new ObjectId(id);
        FindIterable<Document> findIterable = mongoCollection.find(eq("_id", objectId));
        MongoCursor<Document> cursor = findIterable.iterator();
        Person person = null;

        try {
            if (cursor.hasNext()) {
                Document document = cursor.next();
                person = new Person();
                person.setName(document.getString("name"));
                person.setSalary(document.getString("salary"));
                person.setCity(document.getString("city"));
                person.setId(document.getObjectId("_id").toString());
            }
        } finally {
            cursor.close();
        }

        return person;
    }

    @Override
    public Boolean deletePerson(String id) {
        Boolean isDelete = false;
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("person").getCollection("person");
        ObjectId objectId = new ObjectId(id);
        mongoCollection.deleteOne(new Document("_id", objectId));
        isDelete = true;
        return isDelete;
    }

    @Override
    public Boolean updatePerson(String id, Person person) {

        Boolean isUpdated = false;

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id)); // (1)

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", person.getName()); // (2)
        newDocument.put("city",person.getCity());
        newDocument.put("salary",person.getSalary());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument); // (3)

        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("person").getCollection("person");
        mongoCollection.updateOne(query, updateObject); // (4)

        isUpdated = true;

        return true;
    }
}
