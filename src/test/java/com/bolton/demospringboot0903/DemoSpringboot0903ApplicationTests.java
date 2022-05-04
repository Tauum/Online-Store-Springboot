package com.bolton.demospringboot0903;

import com.bolton.demospringboot0903.Models.*;
import com.bolton.demospringboot0903.Repo.*;
import com.bolton.demospringboot0903.Service.*;
import com.bolton.demospringboot0903.controller.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class DemoSpringboot0903ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ItemController itemController;
	@Autowired
	private CartController cartController;
	@Autowired
	private OrderController orderController;
	@Autowired
	private UserController userController;
	@Autowired
	private CategoryController categoryController;

	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	@MockBean
	private ItemRepo itemRepo;

	@MockBean
	private CartRepo cartRepo;

	@MockBean
	private OrderRepo orderRepo;

	@MockBean
	private UserRepo userRepo;

	@MockBean
	private CategoryRepo categoryRepo;

// CATEGORY //
	@Test
	public void addCategory(){
		Category category = new Category(1L, "test");
		when(categoryRepo.save(category)).thenReturn(category);
		assertEquals(category, categoryRepo.save(category));
	}

	@Test
	public void findAllCategory(){
		when(categoryRepo.findAll()).thenReturn(Stream
				.of(new Category(1L,"test1"), new Category(2L, "test2")).collect(Collectors.toList()));
		assertEquals(2, categoryRepo.findAll().size());
	}

	@Test
	public void deleteCategory() {
		Category category = new Category(1L, "test");
		categoryService.deleteCategory(category.getId());
		verify(categoryRepo, times(1)).delete(category);
	}


	// ITEM //
	@Test
	public void addItem(){
		Category category = new Category(1L, "test");
		LocalDate date = LocalDate.of(2020, 1, 1);
		byte[] image = new byte[0];
		Item item = new Item(1L, "title", "description", date , 5.5, 1, 1,15, category );

		when(itemRepo.save(item)).thenReturn(item);
		assertEquals(item, itemRepo.save(item));
	}

	@Test
	public void findAllItem(){

		when(itemRepo.findAll()).thenReturn(Stream
				.of( new Item(1L, "title", "description",
						LocalDate.of(2020, 1, 1)
						, 5.5, 1, 1,15,
						new Category(1L, "test") ))
				.collect(Collectors.toList()));
		assertEquals(1, itemRepo.findAll().size());
	}

	@Test
	public void deleteItem() {
		Category category = new Category(1L, "test");
		LocalDate date = LocalDate.of(2020, 1, 1);
		byte[] image = new byte[0];
		Item item = new Item(1L, "title", "description", date , 5.5, 1, 1,15, category );

		itemService.deleteItem(item.getId());
		verify(itemService, times(1)).deleteItem(item.getId());
	}

	// CART //
	@Test
	public void addCart(){
		Cart cart = new Cart(1L, 1L );
		when(cartRepo.save(cart)).thenReturn(cart);
		assertEquals(cart, cartRepo.save(cart));
	}

	@Test
	public void findAllCart(){

		when(cartRepo.findAll()).thenReturn(Stream
				.of( new Cart(1L, 1L ))
				.collect(Collectors.toList()));
		assertEquals(1, cartRepo.findAll().size());
	}

	@Test
	public void deleteCart() {
		Cart cart = new Cart(1L, 1L );
		cartService.deleteCart(cart.getId());
		verify(cartService, times(1)).deleteCart(cart.getId());
	}


	// ORDER //
	@Test
	public void addOrder(){
		Address address = new Address(1L, 1, "1", "2", "3", "abc123");

		List<Item> items = new ArrayList();

		Order order = new Order(1L, 10.5, 50, 5, 0,
				LocalDate.of(2020, 1, 1),
				"fname", "lname", 1L,
				address, items
		);

		when(orderRepo.save(order)).thenReturn(order);
		assertEquals(order, orderRepo.save(order));
	}

	@Test
	public void findAllOrder(){

		when(orderRepo.findAll()).thenReturn(Stream
				.of(
						new Order(1L, 10.5, 50, 5, 0,
								LocalDate.of(2020, 1, 1),
								"fname", "lname", 1L,
								new Address(1L, 1, "1", "2", "3", "abc123")
								,new ArrayList <Item>())
						)
				.collect(Collectors.toList()));
		assertEquals(1, orderRepo.findAll().size());
	}

	@Test
	public void deleteOrder() {
		Address address = new Address(1L, 1, "1", "2", "3", "abc123");

		List<Item> items = new ArrayList();

		Order order = new Order(1L, 10.5, 50, 5, 0,
				LocalDate.of(2020, 1, 1),
				"fname", "lname", 1L,
				address, items
		);

		orderService.deleteOrder(order.getId());
		verify(orderService, times(1)).deleteOrder(order.getId());
	}

}
