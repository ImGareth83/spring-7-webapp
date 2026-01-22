package guru.springframework.spring7webapp.bootstrap;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner{

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("1234567890");

        Author savedEric = authorRepository.save(eric);
        Book bookSaved = bookRepository.save(ddd);


        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("2468101214");

        Author rodSaved = authorRepository.save(rod);
        Book bookSavedNoEJB = bookRepository.save(noEJB);

        savedEric.getBooks().add(bookSaved);
        rodSaved.getBooks().add(bookSavedNoEJB);
        bookSaved.getAuthors().add(savedEric);
        bookSavedNoEJB.getAuthors().add(rodSaved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("SFG Publishing");
        publisher.setAddress("123 Main St");
        publisher.setCity("San Francisco");
        publisher.setState("CA");
        publisher.setZip("12345");
        Publisher savedPublisher = publisherRepository.save(publisher);

        bookSaved.setPublisher(savedPublisher);
        bookSavedNoEJB.setPublisher(savedPublisher);
        bookRepository.save(bookSaved);
        bookRepository.save(bookSavedNoEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
    }   
}