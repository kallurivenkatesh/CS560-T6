package com.ibm.cloudoe.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class NLPTest extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse reponse) throws InvalidFormatException, IOException, ServletException{
		
		String input = request.getParameter("input");
		
		System.out.println("doPost:"+input);

		ProcessData processData = new ProcessData();
		String sentense[] = processData.getSentences(input);
		
		if(null==sentense || sentense.length==0)
		{
			request.setAttribute("noOfSentences", "0");
		}else{
			request.setAttribute("noOfSentences",  sentense.length);
		}
		
		request.setAttribute("noofwords", processData.getTokens(input).length);
		
		/*
		 * 
		 * Processing names of persons
		 * 
		 */
		
		Span[] namespSpans= processData.getPersonNames(input);
		System.out.println("size is "+namespSpans.length);
		if(namespSpans.length==0){
			request.setAttribute("names","No Names in the sentence");
			//System.out.println("in if loop");
		}else{
			String names=" Count ="+namespSpans.length+"     ";
			for(Span s : namespSpans){
				names=names+s.toString();
			}
			request.setAttribute("names",names);
			//System.out.println(request.getAttribute("names"));
		}
		
		/*
		 * Processing location information
		 * 
		 * 
		 * 
		 */
		Span[] locationNameSpans = processData.getLocationNames(input);
		
		if(locationNameSpans.length==0){
			request.setAttribute("locationnames","No Location Names in the sentence");
			//System.out.println("in if loop");
		}else{
			String locationnames=" Count ="+locationNameSpans.length+"     ";
			for(Span s : locationNameSpans){
				locationnames=locationnames+s.toString();
			}
			request.setAttribute("locationnames",locationnames);
			//System.out.println(request.getAttribute("names"));
		}
		
		/*
		 * 
		 * Processing Organization information
		 * 
		 */
		Span[] orgNameSpans = processData.getOrganizationNames(input);
		
		if(orgNameSpans.length==0){
			request.setAttribute("orgnames","No Organization Names in the sentence");
			//System.out.println("in if loop");
		}else{
			String organames=" Count ="+orgNameSpans.length+"     ";
			for(Span s : orgNameSpans){
				organames=organames+s.toString();
			}
			request.setAttribute("orgnames",organames);
			//System.out.println(request.getAttribute("names"));
		}
		
		/*
		 * 
		 * Processing Money names
		 * 
		 */
		Span[] moneyNameSpans = processData.getMoneyNames(input);
		
		if(moneyNameSpans.length==0){
			request.setAttribute("moneynames","No Money Names in the sentence");
			//System.out.println("in if loop");
		}else{
			String moneynames=" Count ="+moneyNameSpans.length+"     ";
			for(Span s : orgNameSpans){
				moneynames=moneynames+s.toString();
			}
			request.setAttribute("moneynames",moneynames);
			//System.out.println(request.getAttribute("names"));
		}
		
		request.getRequestDispatcher("start.jsp").forward(request,reponse); 
	}

}
