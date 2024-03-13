package com.sky.service.impl;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressBookImpl implements AddressBookService {

    @Autowired
    AddressBookMapper addressBookMapper;

    /**
     * 新增地址
     * @param addressBook
     */
    public void save(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    /**
     * 查询用户所有地址信息
     * @param addressBook
     * @return
     */
    public List<AddressBook> list(AddressBook addressBook) {
        List<AddressBook>listByUserId= addressBookMapper.listByUserId(addressBook);
        return listByUserId;
    }

    /**
     * 查询用户的默认地址
     * @param addressBook
     * @return
     */
    public AddressBook getDefault(AddressBook addressBook) {
        AddressBook addressBook1=new AddressBook();
        addressBook1=addressBookMapper.selectDeafult(addressBook);
        return addressBook1;
    }

    /**
     * 根据用户id修改地址
     * @param addressBook
     */
    public void updateById(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据用户id删除地址
     * @param id
     */
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    public AddressBook getById(Long id) {
        AddressBook addressBook = addressBookMapper.getById(id);
        return addressBook;
    }

    /**
     * 设置默认地址
     * @param addressBook
     */
    @Transactional
    public void setDefault(AddressBook addressBook) {
        //将当前用户所有地址设置为非默认的
        addressBook.setIsDefault(0);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookMapper.updateIsDefaultByUserId(addressBook);

        //设置当前地址为默认的
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);

    }


}
