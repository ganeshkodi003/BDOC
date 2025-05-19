package com.bornfire.config;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Collections;
//import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

 
 
import com.bornfire.entities.HRMS_USER_PROFILE_ENTITY;
import com.bornfire.entities.HRMS_USER_PROFILE_REPOSITORY;
 
import com.bornfire.services.AttendanceRegisterService;
 

@Configuration
@EnableWebSecurity
public class BTMWebSecurity extends WebSecurityConfigurerAdapter {


 

	@Autowired
	SessionFactory sessionFactory;
 
 
	
	@Autowired
	PasswordEncryption passwordEncryption;

	@Autowired
	Environment env;
	// private static final Logger logger =
	// LoggerFactory.getLogger(XBRLReportsController.class);

	@Autowired
	AttendanceRegisterService attendanceRegisterService;
	
	//@Autowired
	//ResourceMasterRepo  resourceMasterRepo;
	
	@Autowired 
	HRMS_USER_PROFILE_REPOSITORY hrms_userprofile_repo;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/webjars/**", "/images/**", "/login*","/RecievingMail*","/associatedetails","/changePasswordReq","/changePasswordLogin", "/freezeColumn/**", "favicon.ico")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.failureHandler(blrsAuthFailHandle()).successHandler(blrsAuthSuccessHandle())
				.usernameParameter("userid").and().logout().permitAll().and().logout()
				.logoutSuccessHandler(xbrlLogoutSuccessHandler()).permitAll().and().sessionManagement()
				.maximumSessions(1).maxSessionsPreventsLogin(false);
		http.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean

	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider ap = new DaoAuthenticationProvider() {

			@Override
			@Transactional
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String userid = authentication.getName();
				
				System.out.println(userid);
				
				String password = authentication.getCredentials().toString();

				/* Optional<UserProfile> up = userProfileRep.findById(userid);  */
				
				Optional<HRMS_USER_PROFILE_ENTITY> Res_data  = hrms_userprofile_repo.findById(userid);

				try {

					if (Res_data.isPresent()) {
						HRMS_USER_PROFILE_ENTITY usr = Res_data.get();
						
						if (!usr.isAccountNonExpired()) {

							throw new AccountExpiredException("Account Expired");

					}	else if (!usr.isAccountNonLocked()) {

							throw new LockedException("Account Locked");

						} else if (!usr.isEnabled()) {

							throw new DisabledException("Account Disabled");

						} else if (!usr.isLoginAllowed()) {

							throw new LockedException("Login Not Allowed");

						} else if (usr.isPasswordExpired()) {

							throw new LockedException("Password Is Expired."
									+ " Please Change Your Password");

						} /*else if (!PasswordEncryption.validatePassword(password, usr.getPassword())) {*/
						else if (!AES.validatePassword(password, usr.getPassword())) {
							

							logger.info("Passing Userid :" + userid);

							/*
							 * Session hs = sessionFactory.getCurrentSession(); Transaction tr =
							 * hs.getTransaction(); hs.createQuery(
							 * "update UserProfile a set a.no_of_attmp=nvl(a.no_of_attmp,0)+1, a.user_locked_flg=decode(nvl(a.no_of_attmp,0)+1,'3','Y','N'), a.login_status=decode(nvl(a.no_of_attmp,0)+1,'3','Inactive','Active') where userid=?1"
							 * ) .setParameter(1, userid).executeUpdate(); tr.commit(); hs.close();
							 */

							throw new BadCredentialsException("Password Wrong");

						} else {

							return new UsernamePasswordAuthenticationToken(userid, password, Collections.emptyList());

						}

					} else {

						throw new UsernameNotFoundException("Invalid User Name");
					}

				} catch (NoSuchAlgorithmException | InvalidKeySpecException | ParseException e) {
					e.printStackTrace();
					authentication.setAuthenticated(false);
				}
				return authentication;

			}

			@Override
			public boolean supports(Class<?> aClass) {
				return aClass.equals(UsernamePasswordAuthenticationToken.class);
			}

		};

		ap.setHideUserNotFoundExceptions(false);
		ap.setUserDetailsService(userDetailsService());

		return ap;
	}

	/*
	 * @Bean
	 * 
	 * @Override public UserDetailsService userDetailsService() {
	 * 
	 * return new UserDetailsService() {
	 * 
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException {
	 * 
	 * Optional<ResourceMaster> up = resourceMasterRepo.findByusername(username);
	 * 
	 * if (up.isPresent()) { return up.get(); } else { return new ResourceMaster();
	 * }
	 * 
	 * }
	 * 
	 * }; }
	 */
	@Bean
	public AuthenticationFailureHandler blrsAuthFailHandle() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {

				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				// logger.info(exception.getMessage());
				response.sendRedirect("login?error=" + exception.getMessage());

			}

		};

	}

	/*@Bean
	public AuthenticationFailureHandler blrsAuthFailHandle() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				
				
				BGLS_Control_Table up1 = bGLS_CONTROL_TABLE_REP.getTranDate();

				request.getSession().setAttribute("TRANDATE", up1.getTran_date());

				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				// logger.info(exception.getMessage());
				response.sendRedirect("login?error=" + exception.getMessage());

			}

		};

	}*/

	@Bean
	public AuthenticationSuccessHandler blrsAuthSuccessHandle() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {

				//Optional<ResourceMaster> up = resourceMasterRepo.findById(authentication.getName());
				
				Optional<HRMS_USER_PROFILE_ENTITY> Res_data  = hrms_userprofile_repo.findById(authentication.getName());
				
				HRMS_USER_PROFILE_ENTITY user = Res_data.get();
				
				/*
				 * user.setNo_of_attmp(0); user.setUser_locked_flg("N");
				 */
				
				//user.setLoginFlg("Y");
				//hrms_userprofile_repo.save(user);
				
			

				// login Time
				// HttpSession session=request.getSession();
				
				  HttpSession session = request.getSession(false); // Avoid creating a new session if one doesn't exist
				 
				    if (session == null) {
				        throw new ServletException("Session is null. Unable to proceed.");
				    }
				    String session_value = session.getId();
				    
				    System.out.println("the checking web secutity" +session_value );
				 
				 
				String Msg = attendanceRegisterService.Registersubmit(user, session_value);
				// System.out.println(Msg+"123");

				/*
				 * loginServices.SessionLogging("LOGIN", "M1", request.getSession().getId(),
				 * user.getUserId(), request.getRemoteAddr(), "ACTIVE");
				 */

				request.getSession().setAttribute("USERID", user.getUserId());
				
				System.out.println("the user id" +user.getUserId());

				request.getSession().setAttribute("USERNAME", user.getUserNames());
				
				System.out.println("the user name" + user.getUserNames());
				request.getSession().setAttribute("ROLEID", user.getRole());
				/* request.getSession().setAttribute("PERMISSIONS", user.getPermissions()); */
				/* request.getSession().setAttribute("WORKCLASS", user.getWork_class()); */
				request.getSession().setAttribute("PASSWORD", user.getPassword());

				response.sendRedirect("Dashboard");
			}

		};

	}

	@Bean
	public LogoutSuccessHandler xbrlLogoutSuccessHandler() {
		
		

		return new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				
				System.out.println(authentication.getName());
				String msg = attendanceRegisterService.logoutsubmit(authentication.getName());
				System.out.println(msg);
				response.sendRedirect("login?logout");
			}
		};
	}

}