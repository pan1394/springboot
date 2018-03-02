package com.yilin.www.spingboot.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yilin.www.spingboot.domain.User;
import com.yilin.www.spingboot.domain.Weibo;
import com.yilin.www.spingboot.dto.ResultModel;
import com.yilin.www.spingboot.repository.UserRepository;
import com.yilin.www.spingboot.repository.WeiboRepository;

@RestController
public class WeiboController {

	@Autowired 
	private WeiboRepository weiboRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/username/{username}")
	public ResponseEntity<ResultModel> getUserWeibo(@PathVariable("username") String username) {
		List<Weibo> body =  this.weiboRepository.searchUserWeibo(username,Sort.by(new Sort.Order(Sort.Direction.ASC,"weiboId")));
		return new ResponseEntity<ResultModel>(ResultModel.ok(body),HttpStatus.OK);
	}
	
	@GetMapping("/simpleSearch")
    public ResponseEntity<ResultModel> simpleSearch(String username,String weiboText,int pageNo,int pageSize){
        User user = this.userRepository.getByUsernameIs(username);
        Page<Weibo> body = this.weiboRepository.findByUserIsAndWeiboTextContaining(user,weiboText,PageRequest.of(pageNo,pageSize,Sort.by(Sort.Order.asc("weiboId"))));
        return new ResponseEntity<ResultModel>(ResultModel.ok(body),HttpStatus.OK);
    }
	
	@GetMapping("/searchWeibo")
    public Page<Weibo> searchWeibo(final String username, final String weiboText, final Date startDate, final Date endDate,int pageNo,int pageSize) {
        Page<Weibo> page = this.weiboRepository.findAll(
         new Specification<Weibo>() {
            @Override
            public Predicate toPredicate(Root<Weibo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new LinkedList<>();

                if (!StringUtils.isEmpty(username)) {
                    //Join有两种方式                   
//                    Join<Weibo,User> userJoin = root.join("user",JoinType.INNER);
//                    predicates.add(criteriaBuilder.equal(userJoin.get("username"), username));
                    predicates.add(criteriaBuilder.equal(root.get("user").get("username"),username));
                }
                if (!StringUtils.isEmpty(weiboText)) {
                    predicates.add(criteriaBuilder.like(root.get("weiboText"), "%" + weiboText + "%"));
                }
                if(startDate!=null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate").as(Date.class),startDate));
                }
                if(endDate != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createDate").as(Date.class),endDate));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, PageRequest.of(pageNo,pageSize));
        return page;
    }
}
