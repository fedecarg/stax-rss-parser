package com.fedecarg.xml.stax.writer;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxWriter {
	private String configFile;

	public void setFile(String configFile) {
		this.configFile = configFile;
	}

	public void saveConfig() throws Exception {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(configFile));
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);

		StartElement configStartElement = eventFactory.createStartElement("",
				"", "config");
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		createNode(eventWriter, "mode", "1");
		createNode(eventWriter, "unit", "901");
		createNode(eventWriter, "current", "0");
		createNode(eventWriter, "interactive", "0");

		eventWriter.add(eventFactory.createEndElement("", "", "config"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name,
			String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);

	}

}
