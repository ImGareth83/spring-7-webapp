package guru.springframework.spring7webapp.bootstrap;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;

@Component
public class BootstrapData implements CommandLineRunner{

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
    }   
}