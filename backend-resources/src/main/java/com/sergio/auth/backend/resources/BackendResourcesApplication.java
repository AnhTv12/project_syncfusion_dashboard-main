package com.sergio.auth.backend.resources;
import com.sergio.auth.backend.resources.model.*;
import com.sergio.auth.backend.resources.repository.RevenueRepo;
import com.sergio.auth.backend.resources.repository.SalesRepo;
import com.sergio.auth.backend.resources.service.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;


@SpringBootApplication
@Slf4j
public class BackendResourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendResourcesApplication.class, args);
	}

	@Autowired
	private BranchService branchService;
	@Autowired
	private ServiceHandle serviceHandle;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private RevenueService revenueService;
	@Autowired
	private SalesService service;

	@Autowired
	private SalesRepo salesRepo;

//	@Bean
	CommandLineRunner runner(){
		return args -> {
//			 Creat Branches
//			Branch branch1 = new Branch(1,"Branch 1","Tokyo","Ta Viet Anh",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//			Branch branch2 = new Branch(2,"Branch 2","Hokkaido","Nguyen Quy Thuc",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//			Branch branch3 = new Branch(3,"Branch 3","Osaka","Do Manh Cuong",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//			Branch branch4 = new Branch(4,"Branch 4","Kyoto","Nguyen Hoang Son",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//			branchService.addBranch(branch1);
//			branchService.addBranch(branch2);
//			branchService.addBranch(branch3);
//			branchService.addBranch(branch4);
			// Creat Services
//			Service service1 = new Service(1,"Personal Training","Our certified Personal Trainers are here to help you reach your fitness goals, fast. Work out on your schedule with a customized plan that helps you build the right form, confidence, and motivation to continue your fitness journey. ","MORNING",new HashSet<>(),new HashSet<>(),100L);
//			Service service2= new Service(2,"Team Training","Rise to the challenge and get some variety in your routine with Team Training. You’ll train in a supportive, non-judgmental environment focused on engaging workouts that keep you accountable!","AFTERNOON",new HashSet<>(),new HashSet<>(),50L);
//			Service service3 = new Service(3,"PEAK Training United","Push past your plateau and take part in a supportive team workout that helps you reach a little higher and dig a little deeper. PEAK Training United improves your mindset, mobility, strength and stamina with next-level, dynamic High Intensity Interval Training.","EVENING",new HashSet<>(),new HashSet<>(),120L);
//			Service service4 = new Service(4,"FIT-FIX","The most efficient circuit training program designed for maximum results in only 20 minutes. Are you in a pinch for time, but still want to get in a good sweat? Great for weight loss and muscle tone, GoodLife’s 20-minute Fit-Fix is an easy-to-follow circuit that makes getting started at your Club simple.","FULL_TIME",new HashSet<>(),new HashSet<>(),89L);
//			Service service5 = new Service(5,"Craft Boxing","CRAFTBOXING Connect your mind and body with Craft Boxing designed by professional boxer, George Foreman III. Focused entirely on the fundamentals, the Craft experience will not only improve your physical health, but also create focus and growth that leads to better mental health.","MORNING",new HashSet<>(),new HashSet<>(),159L);
//			Service service6= new Service(6,"iRiDE","A revolutionary indoor cycling Performance studio. Enjoy cinema-scale screens, theatre-style distribution, state-of-the-art lights & sound system and one of the best indoor bikes. iRiDE’s immersive features enhance your fitness experience taking it to another level!","AFTERNOON",new HashSet<>(),new HashSet<>(),159L);
//			Service service7 = new Service(7,"For Women","Our Women’s Only Classes and spaces inspire, encourage, and empower personal growth in a comfortable setting.","EVENING",new HashSet<>(),new HashSet<>(),119L);
//			Service service8 = new Service(8,"Yoga","Build lean muscle, test your cardiovascular system, and pump your upper/lower body muscular endurance!","FULL_TIME",new HashSet<>(),new HashSet<>(),59L);
//			serviceHandle.addService(service1);
//			serviceHandle.addService(service2);
//			serviceHandle.addService(service3);
//			serviceHandle.addService(service4);
//			serviceHandle.addService(service5);
//			serviceHandle.addService(service6);
//			serviceHandle.addService(service7);
//			serviceHandle.addService(service8);
//			// Creat Employees
//			Employees employees10 = new Employees(1,"Murphy","Diane","",branchService.findBranch(1));
//			Employees employees11 = new Employees(2,"Patterson","Mary","",branchService.findBranch(1));
//			Employees employees12 = new Employees(3,"Firrelli","Jeff","",branchService.findBranch(1));
//			Employees employees13 = new Employees(4,"Patterson","William","",branchService.findBranch(1));
//			Employees employees14 = new Employees(5,"Bondur","Gerard","",branchService.findBranch(1));
//			Employees employees20 = new Employees(6,"Bow","Anthony","",branchService.findBranch(2));
//			Employees employees21 = new Employees(7,"Jennings","Leslie","",branchService.findBranch(2));
//			Employees employees22 = new Employees(8,"Thompson","Leslie","",branchService.findBranch(2));
//			Employees employees23 = new Employees(9,"Firrelli","Julie","",branchService.findBranch(2));
//			Employees employees24 = new Employees(10,"Steve","Patterson","",branchService.findBranch(2));
//			Employees employees30 = new Employees(1,"Tseng","Foon Yue","",branchService.findBranch(3));
//			Employees employees31 = new Employees(2,"Vanauf","George","",branchService.findBranch(3));
//			Employees employees32 = new Employees(3,"Loui","Bondur","",branchService.findBranch(3));
//			Employees employees33 = new Employees(4,"Hernandez","Gerard","",branchService.findBranch(3));
//			Employees employees34 = new Employees(5,"Castillo","Pamela","",branchService.findBranch(3));
//			Employees employees40 = new Employees(6,"Bott","Larry","",branchService.findBranch(4));
//			Employees employees41 = new Employees(7,"Jones","Barry","",branchService.findBranch(4));
//			Employees employees42 = new Employees(8,"Fixter","Peter","",branchService.findBranch(4));
//			Employees employees43 = new Employees(9,"King","Tom","",branchService.findBranch(4));
//			Employees employees44 = new Employees(10,"Nishi","Mami","",branchService.findBranch(4));
//			employeeService.addAll(employees10,employees11,employees12,employees13,employees14,
//					employees20,employees21,employees22,employees23,employees24,
//					employees30,employees31,employees32,employees33,employees34,
//					employees40,employees41,employees42,employees43,employees44);
//			//Creat Customers
//			Customers customers1 = new Customers(1,"Schmitt Carine","dmurphy12311@gmail.com","", Calendar.getInstance(),"098659236",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(1));
//			Customers customers2 = new Customers(2,"King Jean","dmurphy12@gmail.com","", Calendar.getInstance(),"03 9520 4555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(2));
//			Customers customers3 = new Customers(3,"Ferguson Peter","ferguson@gmail.com","", Calendar.getInstance(),"07-98 9555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers4 = new Customers(4,"Labrune Janine ","jfirrelli@gmail.com","", Calendar.getInstance(),"4155551450",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(4));
//			Customers customers5 = new Customers(5,"King Jean","dmurphy123@gmail.com","", Calendar.getInstance(),"6505555787",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers6 = new Customers(6,"Bergulfsen Jonas ","dmurphy1234@gmail.com","", Calendar.getInstance(),"0921-12 3555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(2));
//			Customers customers7 = new Customers(7,"Nelson Susan","dmursgbphy@gmail.com","", Calendar.getInstance(),"6505556809",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(1));
//			Customers customers8 = new Customers(8,"Piestrzeniewicz Zbyszek ","byszek@gmail.com","", Calendar.getInstance(),"+65 224 1555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(2));
//			Customers customers9 = new Customers(9,"Keitel Roland","keitel@gmail.com","", Calendar.getInstance(),"+47 2267 3215",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(1));
//			Customers customers10 = new Customers(10,"Lee Kwai","admurphykwai@gmail.com","", Calendar.getInstance(),"2035557845",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers11 = new Customers(11,"Freyre Diego ","admurphy@gmail.com","", Calendar.getInstance(),"6175555555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(4));
//			Customers customers12 = new Customers(12,"Berglund Christina ","erglund@gmail.com","", Calendar.getInstance(),"6175557555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(4));
//			Customers customers13 = new Customers(13,"Petersen Jytte ","etersen@gmail.com","", Calendar.getInstance(),"3105552373",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(1));
//			Customers customers14 = new Customers(14,"Saveley Mary ","daveley@gmail.com","", Calendar.getInstance(),"0372-555188",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(2));
//			Customers customers15 = new Customers(15,"Natividad Eric","atividad@gmail.com","", Calendar.getInstance(),"(91) 745 6555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers16 = new Customers(16,"Young Jeff","dmoung@gmail.com","", Calendar.getInstance(),"7605558146",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(1));
//			Customers customers17 = new Customers(17,"King Jean","dmurphy@gmail.com","", Calendar.getInstance(),"+33 1 46 62 7555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(2));
//			Customers customers18 = new Customers(18,"Leong Kelvin","dmueongy@gmail.com","", Calendar.getInstance(),"0221-5554327",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(4));
//			Customers customers19 = new Customers(19,"Hashimoto Juri","dmurashimoto@gmail.com","", Calendar.getInstance(),"089-0877555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers20 = new Customers(20,"Victorino Wendy","dmurendyphy@gmail.com","", Calendar.getInstance(),"02 9936 8555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers21 = new Customers(21,"Oeztan Veysel","deyselmeztanurphy@gmail.com","", Calendar.getInstance(),"030-0074555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(1));
//			Customers customers22 = new Customers(22,"Franco Keith","eithmurph@gmail.com","", Calendar.getInstance(),"5085559555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(2));
//			Customers customers23 = new Customers(23,"Bertrand Marie","dertrandury@gmail.com","", Calendar.getInstance(),"+358 9 8045 555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(3));
//			Customers customers24 = new Customers(24,"Tseng Jerry","dsengmuerryphy@gmail.com","", Calendar.getInstance(),"0555-09555",
//					new ArrayList<>(),new ArrayList<>(),branchService.findBranch(4));
//			customerService.addAll(customers1,customers2,customers3,customers4,customers5,customers6,customers7,customers8,customers9,customers10,customers11,customers12,
//					customers13,customers14,customers15,customers16,customers17,customers18,customers19,customers20,customers21,customers22,customers23,customers24);
//
//
			// Creat Order Data)
			for (int j = 2015; j < 2023; j++) {
				for (int i = 1; i <= 50; i++) {
					Random random = new Random();
					int branchId = random.nextInt(4) + 1;
					int serviceId = random.nextInt(5) + 4;
					int customerId = random.nextInt(23) + 1;
					long discount = random.nextLong(20);
					Calendar calendar = new GregorianCalendar(j, 11, random.nextInt(29) + 1);
					Order order = new Order(branchService.findBranch(branchId), serviceHandle.findService(8), customerService.findCustomer(customerId),
							calendar, 0, discount, 0);
					log.info("year: {} - {} time",j, i);
					orderService.addOrder(order);
				}
			}


		};
	}
}
