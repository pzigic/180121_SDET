package com.revature.main;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.bean.Book;
import com.revature.exception.LibraryYearException;
import com.revature.service.Library;

public class Driver {

	public static void main(String[] args) {
		String serviceUrl = "http://localhost:8085/180121_Week7_SOAPServer/Library";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Library.class);
		factory.setAddress(serviceUrl);
		
		LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();
		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);
		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));
		
		//Consuming SOAP
		Library library = (Library) factory.create();
		List<Book> bookList = library.getAllBooks();
		
		for(Book b: bookList){
			System.out.println(b);
		}
		
		//post a book
		try {
			System.out.println(library.addBook(new Book("My New book!", "Me", 2005)));
			System.out.println(library.addBook(new Book("My bad book!", "Me as well", 1999)));
		} catch (LibraryYearException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
