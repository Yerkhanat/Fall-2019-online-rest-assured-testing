package com.automation.pojos;

import java.util.List;

/*{
    "studentId": 11617,
    "firstName": "MESSI",
    "lastName": "10",
    "batch": 15,
    "joinDate": "01/01/3321",
    "birthDate": "01/01/1890",
    "password": "1234",
    "subject": "Forward",
    "gender": "Males",
    "admissionNo": "1111",
    "major": "SOCCER",
    "section": "101010",
    "contact": {
        "contactId": 11637,
        "phone": "240-123-1231",
        "emailAddress": "messi_goat@gmail.com",
        "premanentAddress": "7925 Jones Branch Dr"
    },
    "company": {
        "companyId": 11557,
        "companyName": "Barcelona",
        "title": "SDET",
        "startDate": "02/02/2001",
        "address": {
            "addressId": 11577,
            "street": "7925 Jones Branch Dr",
            "city": "McLean",
            "state": "Virginia",
            "zipCode": 22102
        }
    }
}*/
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private int batch;
    private String joinDate;
    private String birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String major;
    private String section;
    private Contact contact;
    private Company company;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", batch=" + batch +
                ", joinDate='" + joinDate + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", gender='" + gender + '\'' +
                ", admissionNo='" + admissionNo + '\'' +
                ", major='" + major + '\'' +
                ", section='" + section + '\'' +
                ", contact=" + contact +
                ", company=" + company +
                '}';
    }
}
