package com.fedecarg.xml.stax.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.fedecarg.xml.stax.model.Item;

public class StaXParser {
    
    static final String DATE = "date";
    static final String ITEM = "item";
    static final String MODE = "mode";
    static final String UNIT = "unit";
    static final String CURRENT = "current";
    static final String INTERACTIVE = "interactive";

    @SuppressWarnings({ "unchecked" })
    public List<Item> readConfig(String configFile) {
        
        List<Item> items = new ArrayList<Item>();
        
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Item item = null;
            
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart() == (ITEM)) {
                        item = new Item();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals(DATE));
                            item.setDate(attribute.getValue());
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(MODE)) {
                            event = eventReader.nextEvent();
                            item.setMode(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(UNIT)) {
                        event = eventReader.nextEvent();
                        item.setUnit(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(CURRENT)) {
                        event = eventReader.nextEvent();
                        item.setCurrent(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(INTERACTIVE)) {
                        event = eventReader.nextEvent();
                        item.setInteractive(event.asCharacters().getData());
                        continue;
                    }
                }
                
                if (event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart() ==(ITEM)){
                        items.add(item);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return items; 
    }

}

