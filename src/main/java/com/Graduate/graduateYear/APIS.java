package com.Graduate.graduateYear;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Graduate.graduateYear.tables.Category;
import com.Graduate.graduateYear.tables.ItemImageRepository;
import com.Graduate.graduateYear.tables.categoryRepo;
import com.Graduate.graduateYear.tables.item;
import com.Graduate.graduateYear.tables.itemImages;
import com.Graduate.graduateYear.tables.itemRepository;
import com.Graduate.graduateYear.tables.user;
import com.Graduate.graduateYear.tables.userRepository;

import net.minidev.json.JSONObject;



@CrossOrigin
@RestController
public class APIS {

	@Autowired
	categoryRepo categoryrepo;
	
	@Autowired
	itemRepository itemrepository;
	
	@Autowired
	userRepository userreposiroty;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	ItemImageRepository itemImagesRepository;
	
	@PostMapping(value = "/auth/login" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest auth) throws InvalidKeySpecException, NoSuchAlgorithmException{
			Authentication authentication =authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPass())
			);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			MyUserDetail myUserDetail=  (MyUserDetail) authentication.getPrincipal();
			String u=myUserDetail.email;
			
			String jwtToken=jwtTokenHelper.generateToken(u);
			
			LoginResponse loginResponse=new LoginResponse();
			loginResponse.setToken(jwtToken);
			loginResponse.setUsername(userreposiroty.findByEmail(u).get().getUserName());
		return new ResponseEntity<>(loginResponse,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getCategories")
	public List<Category> getCategories() {
		List<Category>l= categoryrepo.findAll();
		List<Category>res = l.stream()
		
				.collect(Collectors.toList());
		return res;	
	}
	
	@GetMapping(value = "/getCategoryImage",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getCategoryImage(@RequestParam("id") String id) throws Exception {
		Category c= categoryrepo.findById(Integer.parseInt(id)).get();
		
		String url=c.getImg();
		
		File f=new File(url);
		
		InputStream in= new FileInputStream(f);
		return IOUtils.toByteArray(in);
	}
	/*
	@GetMapping(value = "/getItemImage",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getItemImage(@RequestParam("id") String id) throws Exception {
		item i= itemrepository.findById(Integer.parseInt(id)).get();
		String url=i.getImage();
		File f=new File(url);		
		InputStream in= new FileInputStream(f);
		return IOUtils.toByteArray(in);
	}
	
	@GetMapping("/getItems/{categoryName}")
	public List<item> getItemsOfCategory(@PathVariable String categoryName,@RequestParam("q") Optional<String>word){

		
		List<item>l;
		if(word.isPresent()) {
			l=itemrepository.findItemsWithPartOfNameAndCategory(word.get(),categoryrepo.findByName(categoryName));
		}
		else {
			l=itemrepository.findByCategory(categoryrepo.findByName(categoryName));
		}
		return l
				.stream().filter(i->i.isTook()==false)
				.map(i->{
					String url=i.getImage();
					Path p=Paths.get(url);
					byte[] bArray=null;
					try {
						bArray = Files.readAllBytes(p);
					}catch (Exception e) {
						// TODO: handle exception
					}
					String photo= Base64.encodeBase64String(bArray);
					i.setImage(photo);
					return i;
				})
				.collect(Collectors.toList());
	}
	*/
	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody user  u) throws InvalidKeySpecException, NoSuchAlgorithmException{
	
		String email=u.getEmail();
		boolean exist=userreposiroty.findByEmail(email).isPresent();
		if(exist) {
			return   new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userreposiroty.save(u);
		LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(jwtTokenHelper.generateToken(email));
		return new ResponseEntity<>(loginResponse,HttpStatus.OK);
	}
	
	@PostMapping(value = "/setUserImage")
	public ResponseEntity<?> setUserImage(@RequestParam("image") MultipartFile img,@RequestParam("email")String email) throws IllegalStateException, IOException{
		Optional<user>op=userreposiroty.findByEmail(email);
		if(!op.isPresent()) {
			return ResponseEntity.badRequest().body(null);
		}
		String url="C:\\project\\"+img.getOriginalFilename();
        File f=new File(url);
        img.transferTo(f);
        userreposiroty.updateImg(email, url);
        
        return ResponseEntity.accepted().body(HttpStatus.OK);
	}
	@PostMapping(value = "/createPost")
	public ResponseEntity<?> createPost(Principal u,@RequestParam("title")String title,@RequestParam("description")String description,
			@RequestParam("category")String category,@RequestParam("photo1")Optional<MultipartFile> photo1,
			@RequestParam("photo2")Optional<MultipartFile> photo2 , @RequestParam("photo3")Optional<MultipartFile> photo3
	) throws Exception{
		/*
		item i=new item();
		i.setName(title);
		i.setDescription(description);
		user author=userreposiroty.findByEmail(u.getName()).get();
		i.setAuthor(author);
		Category c=categoryrepo.findByName(category).get();
		i.setCategory(c);
		itemrepository.save(i);
		
		
		if(photo1.isPresent()) {
			itemImages images=new itemImages();
			String url="C:\\project\\itemImages\\"+photo1.get().getOriginalFilename();
	        File f=new File(url);
	        photo1.get().transferTo(f);
	        images.setImage(url);
	        itemImagesRepository.save(images);
		}
		if(photo2.isPresent()) {
			itemImages images=new itemImages();
			String url="C:\\project\\itemImages\\"+photo2.get().getOriginalFilename();
	        File f=new File(url);
	        photo2.get().transferTo(f);
	        images.setImage(url);
	        itemImagesRepository.save(images);
		}
		if(photo3.isPresent()) {
			itemImages images=new itemImages();
			String url="C:\\project\\itemImages"+photo3.get().getOriginalFilename();
	        File f=new File(url);
	        photo3.get().transferTo(f);
	        images.setImage(url);
	        itemImagesRepository.save(images);
		}
		*/
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
}
