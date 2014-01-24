package org.mule.modules.odata.automation.testcases.provider;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.core4j.Func;
import org.odata4j.jersey.producer.resources.ODataApplication;
import org.odata4j.jersey.producer.server.JerseyServer;
import org.odata4j.producer.inmemory.InMemoryProducer;
import org.odata4j.producer.jpa.JPAProducer;
import org.odata4j.producer.resources.RootApplication;
import org.odata4j.producer.server.ODataServer;

public class ODataServiceProvider {

	public static JPAProducer createJpaProducer() {
		String persistenceUnitName = "PersonServiceEclipseLink";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		String namespace = "Person";
		JPAProducer producer = new JPAProducer(entityManagerFactory, namespace, 50);
	
		return producer;
	}
	
	/*
	 * Unused method due to unimplemented methods in InMemoryProducer class.
	 */
	public static InMemoryProducer createProducer() {
		InMemoryProducer producer = new InMemoryProducer(ODataServiceProvider.class.getName());
		
		producer.register(Person.class,  "Persons", new Func<Iterable<Person>>() {

			@Override
			public Iterable<Person> apply() {
				List<Person> persons = new ArrayList<Person>();
				
				persons.add(new Person("1", "John", "Doe"));
				persons.add(new Person("2", "Jane", "Doe"));
				persons.add(new Person("3", "John", "Smith"));
				
				return persons;
			}

		}, "Id");
		return producer;
	}
	
	public static ODataServer getServer(String baseUri) {
		ODataServer server = new JerseyServer(baseUri, ODataApplication.class, RootApplication.class);
		return server;
	}

}

