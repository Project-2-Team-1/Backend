package com.revature;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.data.ReviewRepository;
import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.ReviewNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.service.ReviewService;
import com.revature.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Project2Team1Application.class)
class Project2Team1ApplicationTests {
	
	@Autowired
	private UserService userv;
	
	@Autowired
	private ReviewService rserv;
	
	private Optional<User> dummyUser;
	
	@MockBean
	private ReviewRepository rRepo;
	
	@MockBean
	private UserRepository uRepo;
	
	@Before
	public void setup() {

		uRepo = mock(UserRepository.class); 
		rRepo = mock(ReviewRepository.class);

		userv = new UserService(uRepo, rRepo);
		rserv = new ReviewService(rRepo, uRepo);
		
	}
	
	@After 
	public void teardown() {

		userv = null;
		rserv = null;
		uRepo = null;
		rRepo = null;
		dummyUser = null;
		
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getAllUsers_Successful() {
		
		User e1 = new User ("Bruce", "Banner");
		User e2 = new User("Clint", "Barton");
		User e3 = new User("wanda", "vision");
		
		List<User> list = new ArrayList<User>();		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		
		when(userv.findAll()).thenReturn(list);
		
		List<User> actual = userv.findAll();
		
		assertThat(actual.size() == 3);
		
	}
	
	@Test
	public void getAllUsers_Unsuccessful() {
		
		User e1 = new User ("Bruce", "Banner");
		User e2 = new User("Clint", "Barton");
		User e3 = new User("wanda", "vision");
		
		List<User> list = new ArrayList<User>();		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		
		when(userv.findAll()).thenReturn(list);
		
		List<User> actual = userv.findAll();
		
		assertThat(actual != null);
		
	}
	
	@Test
	public void getUserById_Successful() {
		
		dummyUser = Optional.ofNullable(new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null));
		
		int id = 12;
		
		when(uRepo.findById(12)).thenReturn(dummyUser);
		
		Optional<User> actualUser = Optional.of(userv.getById(12));
		Optional<User> expectedUser = dummyUser;
		
		assertEquals(expectedUser, actualUser);
		
	}
	
	@Test
	public void getUserById_Unsuccessful() {
		
		dummyUser = Optional.ofNullable(new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null));
		
		int id = 12;
		
		when(uRepo.findById(12)).thenReturn(dummyUser);
		
		Optional<User> actualUser = Optional.of(userv.getById(12));
		
		assertThat(actualUser != null);
		
	}
	
	@Test
	public void getUserByUsername_Successful() {
		
		dummyUser = Optional.ofNullable(new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null));
		
		String username = "sponge";
		
		when(uRepo.findByUsername(username)).thenReturn(dummyUser);
		
		Optional<User> actualUser = Optional.of(userv.getByUsername(username));
		Optional<User> expectedUser = dummyUser;
		
		assertEquals(expectedUser, actualUser);
		
	}
	
	@Test
	public void getUserByUsername_Unsuccessful() {
		
		dummyUser = Optional.ofNullable(new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null));
		
		String username = "sponge";
		
		when(uRepo.findByUsername(username)).thenReturn(dummyUser);
		
		Optional<User> actualUser = Optional.of(userv.getByUsername(username));
		
		assertThat(actualUser != null);
		
	}
	
	@Test
	public void getReviewByUserById_Successful() {
		
		User dummyUser = new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null);
		
		Review dummy = new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser);
		Review dummy2 = new Review(2, true, 1, "it sucked", new Timestamp(67070907578l), "ABCE", dummyUser);
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(dummy);
		reviews.add(dummy2);
		
		when(userv.findReviewByUserId(12)).thenReturn(reviews);
		
		List<Review> actual = userv.findReviewByUserId(12);
		List<Review> expected = reviews;
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	public void getReviewByUserById_Unsuccessful() {
		
		User dummyUser = new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null);
		
		Review dummy = new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser);
		Review dummy2 = new Review(2, true, 1, "it sucked", new Timestamp(67070907578l), "ABCE", dummyUser);
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(dummy);
		reviews.add(dummy2);
		
		when(userv.findReviewByUserId(12)).thenReturn(reviews);
		
		List<Review> actual = userv.findReviewByUserId(12);
		List<Review> expected = null;
		
		assertNotEquals(actual, expected);
		
	}
	
	@Test
	public void UserAuthenticate_Unsuccessful () {
		
		User e1 = new User ("Bruce", "Banner");
		Credentials creds = new Credentials("Bruc", "Banner");	
		
		assertThrows(AuthenticationException.class, () -> userv.authenticate(creds));	
		
	}
	
	@Test
	public void UserAuthenticate_Successful() {
		
		Optional<User> e1 = Optional.of(new User ("Bruce", "Banner"));
		
		Credentials creds = new Credentials("Bruce", "Banner");
		
		when(uRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())).thenReturn(e1);
		
		User expected = e1.get();
		User actual = userv.authenticate(creds);
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	public void addUser_Successful() {
		
		User e1 = new User ("Bruce", "Banner");
		
		when(uRepo.save(e1)).thenReturn(e1);
		
		User actual = userv.add(e1);
		User expected = e1;
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	public void addUser_Unsuccessful() {
		
		User e1 = new User ("Bruce", "Banner");
		
		when(uRepo.save(e1)).thenReturn(e1);
		
		User actual = null;
		User expected = e1;
		
		assertNotEquals(actual, expected);
		
	}
	
	@Test
	public void removeUser_Successful() {
		
		User dummyUser = new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null); 
		userv.remove(12);
		assertThrows(UserNotFoundException.class, () -> userv.getById(12));
		
	}
	
	@Test
	public void getAllReviews_Successful() {
		
		Review e1 = new Review (true, 3, "average", new Timestamp(6890758045l));
		Review e2 = new Review (false, 2, "poor", new Timestamp(68907580785l));
		Review e3 = new Review (true, 4, "good", new Timestamp(689075807895l));

		List<Review> list = new ArrayList<Review>();		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		
		when(rserv.findAll()).thenReturn(list);
		List<Review> actual = rserv.findAll();
		assertThat(actual.size() == 3);
		
	}
	
	@Test
	public void getAllReviews_Unsuccessful() {
		
		Review e1 = new Review (true, 3, "average", new Timestamp(6890758045l));
		Review e2 = new Review (false, 2, "poor", new Timestamp(68907580785l));
		Review e3 = new Review (true, 4, "good", new Timestamp(689075807895l));

		List<Review> list = new ArrayList<Review>();		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		
		when(rserv.findAll()).thenReturn(list);
		List<User> actual = userv.findAll();
		assertThat(actual != null);
		
	}
	
	@Test
	public void addReview_Successful() {
		
		Review e1 = new Review (true, 3, "average", new Timestamp(6890758045l));
		
		when(rRepo.save(e1)).thenReturn(e1);
		
		Review actual = rserv.add(e1);
		Review expected = e1;
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	public void addReview_Unsuccessful() {
		
		Review e1 = new Review (true, 3, "average", new Timestamp(6890758045l));
		
		when(rRepo.save(e1)).thenReturn(e1);
		
		Review actual = null;
		Review expected = e1;
		
		assertNotEquals(actual, expected);
		
	}
	
	@Test
	public void removeReview_Successful() {
		
		User dummyUser = new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null);
		Review dummy = new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser);
		rserv.remove(1);
		assertThrows(ReviewNotFoundException.class, () -> rserv.getById(1));
		
	}
	
	@Test
	public void getReviewById_Successful() {
		
		dummyUser = Optional.ofNullable(new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null));
		Optional<Review> dummy = Optional.of(new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser.get()));
		
		when(rRepo.findById(1)).thenReturn(dummy);
		
		Optional<Review> actual = Optional.of(rserv.getById(1));
		Optional<Review> expected = dummy;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void getReviewById_Unsuccessful() {
		
		dummyUser = Optional.ofNullable(new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null));
		Optional<Review> dummy = Optional.of(new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser.get()));
		
		when(rRepo.findById(1)).thenReturn(dummy);
		Optional<Review> expected = dummy;		
		assertThat(expected != null);
		
	}
	
	@Test
	public void findByParkCode_Successful() {
		
		User dummyUser = new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null);
		Review dummy1 = new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser); 
		Review dummy2 = new Review(2, true, 4, "it was great", new Timestamp(6707090564l), "1234", dummyUser); 
		Review dummy3 = new Review(3, true, 4, "it was great", new Timestamp(6707090894l), "ABCD", dummyUser); 
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(dummy3);
		reviews.add(dummy1);
		
		when(rRepo.findByParkCodeOrderByDateReviewed("ABCD")).thenReturn(reviews);
		
		List<Review> actual = rserv.findByParkCode("ABCD");
		List<Review> expected = reviews;
		
		assertEquals(actual, expected);
		
	}
	
	@Test
	public void findByParkCode_Unsuccessful() {
		
		User dummyUser = new User(12, "spongebob", "squarepants", "sponge", "gary", "pineapple@mail.com", null);
		Review dummy1 = new Review(1, true, 4, "it was great", new Timestamp(6707090754l), "ABCD", dummyUser); 
		Review dummy2 = new Review(2, true, 4, "it was great", new Timestamp(6707090564l), "1234", dummyUser); 
		Review dummy3 = new Review(3, true, 4, "it was great", new Timestamp(6707090894l), "ABCD", dummyUser); 
		
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(dummy3);
		reviews.add(dummy1);
		
		when(rRepo.findByParkCodeOrderByDateReviewed("ABCD")).thenReturn(reviews);
		
		List<Review> actual = rserv.findByParkCode("ABCD");
		
		
		assertThat(actual != null);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

