package com.ibm.cloudoe.samples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class ProcessData {

	
	
	ClassLoader classloader = getClass().getClassLoader();
	public String[] getSentences(String text) throws InvalidFormatException, IOException{
		
		System.out.println("Sentense:"+text);
		//ClassLoader classloader = getClass().getClassLoader();
		InputStream bin=new FileInputStream(classloader.getResource("en-sent.bin").getFile());
		SentenceModel sm= new SentenceModel(bin);
		SentenceDetectorME st= new SentenceDetectorME(sm);
		String[] sentences= st.sentDetect(text);
		return sentences;
	}
	
	
	public String[] getTokens(String text) throws IOException{
		//ClassLoader classLoader = getClass().getClassLoader();
		System.out.println("Tokens:"+text);
		TokenizerModel tm=new TokenizerModel(new FileInputStream(classloader.getResource("en-token.bin").getFile()));
		TokenizerME t=new TokenizerME(tm);
		String[] tokens=t.tokenize(text);
		return tokens;
	}
	
	public Span[] getPersonNames(String input) throws IOException{
		
		System.out.println("Persons:"+input);
		String[] tokens=getTokens(input);
		TokenNameFinderModel model=new TokenNameFinderModel(new FileInputStream(classloader.getResource("en-ner-person.bin").getFile()));
		NameFinderME nmf=new NameFinderME(model);
		Span[] namespSpans=nmf.find(tokens);
		return namespSpans;
	}
	
	public Span[] getLocationNames(String input) throws IOException{
		
		System.out.println("Locations:"+input);
		String[] tokens=getTokens(input);
		TokenNameFinderModel model=new TokenNameFinderModel(new FileInputStream(classloader.getResource("en-ner-location.bin").getFile()));
		NameFinderME nmf=new NameFinderME(model);
		Span[] namespSpans=nmf.find(tokens);
		return namespSpans;
	}
	
	public Span[] getOrganizationNames(String input) throws IOException{
		System.out.println("Organizations:"+input);
		String[] tokens=getTokens(input);
		TokenNameFinderModel model=new TokenNameFinderModel(new FileInputStream(classloader.getResource("en-ner-organization.bin").getFile()));
		NameFinderME nmf=new NameFinderME(model);
		Span[] namespSpans=nmf.find(tokens);
		return namespSpans;
	}
	
	public Span[] getMoneyNames(String input) throws IOException{
		String[] tokens=getTokens(input);
		TokenNameFinderModel model=new TokenNameFinderModel(new FileInputStream(classloader.getResource("en-ner-money.bin").getFile()));
		NameFinderME nmf=new NameFinderME(model);
		Span[] namespSpans=nmf.find(tokens);
		return namespSpans;
	} 
}
