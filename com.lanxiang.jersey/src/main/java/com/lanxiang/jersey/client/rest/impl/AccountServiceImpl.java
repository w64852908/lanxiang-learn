package com.lanxiang.jersey.client.rest.impl;

import com.lanxiang.jersey.client.model.Account;
import com.lanxiang.jersey.client.rest.AccountService;
import io.swagger.annotations.ApiParam;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lanxiang on 2017/3/4.
 */
public class AccountServiceImpl implements AccountService {

    private List<Account> accountList;

    private static AtomicLong counter;

    @PostConstruct
    public void init() {
        counter = new AtomicLong(0);
        fillAccountList();
    }

    private void fillAccountList() {
        accountList = new ArrayList<>();
        Account account1 = new Account();
        account1.setId(counter.incrementAndGet());
        account1.setName("兰兢");
        account1.setPhone("18615351030");
        account1.setTenantId(714352L);

        Account account2 = new Account();
        account2.setId(counter.incrementAndGet());
        account2.setName("jojo");
        account2.setPhone("123324234234");
        account2.setTenantId(714352L);

        Account account3 = new Account();
        account3.setId(counter.incrementAndGet());
        account3.setName("dio");
        account3.setPhone("134523452323");
        account3.setTenantId(714352L);
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
    }

    @Override
    public List<Account> getActiveAccounts(Long tenantId) {
        List<Account> result = new ArrayList<>();
        for (Account account : accountList) {
            if (account.getTenantId().equals(tenantId)) {
                result.add(account);
            }
        }
        return result;
    }

    @Override
    public String getBaiduIndexAsString() {
        return "baidu";
    }

    @Override
    public List<Account> create(@ApiParam Account account) {
        accountList.add(account);
        List<Account> result = new ArrayList<>();
        for (Account temp : accountList) {
            if (temp.getTenantId().equals(account.getTenantId())) {
                result.add(temp);
            }
        }
        return result;
    }
}
