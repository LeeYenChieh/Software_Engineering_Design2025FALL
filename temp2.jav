



@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void doWork() {
        userService.createUser("Alice"); 
    }
}

