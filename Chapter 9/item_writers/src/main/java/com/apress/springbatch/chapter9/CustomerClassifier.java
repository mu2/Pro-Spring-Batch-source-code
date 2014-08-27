package com.apress.springbatch.chapter9;

import org.springframework.batch.classify.Classifier;
import org.springframework.batch.item.ItemWriter;

public class CustomerClassifier implements Classifier<Customer, ItemWriter<Customer>> {

    private ItemWriter<Customer> customerItemWriter;
    private ItemWriter<Customer> prospectItemWriter;

    public ItemWriter<Customer> classify(Customer customer) {
        if(customer.getState().matches("^[A-M].*")) {
            return customerItemWriter;
        } else {
            return prospectItemWriter;
        }
    }

    public void setCustomerItemWriter(ItemWriter<Customer> customerItemWriter) {
        this.customerItemWriter = customerItemWriter;
    }

    public void setProspectItemWriter(ItemWriter<Customer> prospectItemWriter) {
        this.prospectItemWriter = prospectItemWriter;
    }
}
