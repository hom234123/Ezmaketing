package spring.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.dao.AccountDao;
import spring.entities.Account;
import spring.entities.AccountDetails;
import spring.entities.AccountRole;

@Service
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {            
        return getAccount(username);
    }

    // Lấy thông tin chi tiết của tài khoản người dùng
    private AccountDetails getAccount(String username) {
        Account ass = accountDao.get(username);
        if (ass == null) {
        	throw new UsernameNotFoundException("User not found");
        }
        
        // Xử lý lấy roles của người dùng đưa vào Authority
        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        Set<AccountRole> roles = ass.getAccountRoles();
        for (AccountRole accountRole : roles) {
            String rolename = accountRole.getRole().getRolename();
            grantedAuthoritySet.add(new SimpleGrantedAuthority(rolename));
        }
        return new AccountDetails(       		
                grantedAuthoritySet,
                ass.getUsername(),
                ass.getAccountid(),
                ass.getGender(),
                ass.getAddress(),
                ass.getEmail(),
                ass.getPhone(),
                ass.getPassWord(), // Mật khẩu đã mã hóa từ cơ sở dữ liệu
                ass.getStatus(),
                true, // Account is non-expired
                true, // Credentials are non-expired
                true  // Account is non-locked
        );
    }
    
    
}
