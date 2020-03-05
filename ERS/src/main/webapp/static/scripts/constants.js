/**
 * This file holds constants that help keep things centralized between my java
 * code and my javascript code.
 */

// URI to home page
const HOME = "/ERS";
// header for the authorization token
const AUTH_TOKEN_HEADER = "session-token";
// header for getting the first name of an employee
const FIRST_NAME_HEADER = "first-name";
// header for getting the last name of an employee
const LAST_NAME_HEADER = "last-name";
// URI for logging in a user
const LOGIN_API = "/ERS/api/login";
// header for sending the email when logging in
const EMAIL_HEADER = "email";
// header for sending the password when logging in
const PASSWORD_HEADER = "password";
// URI for logging out a user
const LOGOUT_API = "/ERS/api/logout";
// resource for displaying the submit reimbursement page
const SUBMIT_REIMBURSEMENT_PAGE = "/ERS/submit";
// URI to actually submit a reimbursement request
const SUBMIT_REIMBURSEMENT_API = "/ERS/api/submit";
// the maximum length accepted for the description field when submitting a reimbursement request
const MAX_LENGTH_DESC = "140";
// name used for form submission for the description when requesting a reimbursement
const DESCRIPTION_ID = "description";
// name for the amount field when submitting a reimbursement request
const AMOUNT_ID = "amount";
// name for the date field when submitting a reimbursement request
const DATE_ID = "date";
// name of cookie that holds the message (for home page alert displays)
const SUCCESS_COOKIE = "success";
// value signaling a failure (value of SUCCESS_COOKIE)
const FAIL_VALUE = "fail";
// value signaling a success (value of SUCCESS_COKKIE)
const SUCCESS_VALUE = "success";
// URI of view reimbursement resource
const VIEW_PENDING_PAGE = "/ERS/pending";
// URI of view reimbursement resource
const VIEW_PROCESSED_PAGE = "/ERS/processed";
// API call to view pending requests by employee
const VIEW_PENDING_BY_EMP = "/ERS/api/view/pending";
// API call to view processed requests by employee
const VIEW_PROCESSED_BY_EMP = "/ERS/api/view/processed";
// URI for returning all reimbursements for an employee
const VIEW_ALL_REIMBURSEMENTS_BY_EMP = "/ERS/api/view";
// URI of resource for getting the profile page
const PROFILE_PAGE = "/ERS/profile";
// name of the cookie which holds the email information
const EMAIL_COOKIE = "email";
// value (name attribute) for changing the first name
const CHANGE_FIRST_NAME_ID = "change-first";
// value (name attribute) for changing the last name
const CHANGE_LAST_NAME_ID = "change-last";
// value (name attribute) for changing the email
const CHANGE_EMAIL_ID = "change-email";
// value (name attribute) for changing the password
const CHANGE_PASSWORD_ID = "change-password";
// URI for the api that changes the profile
const CHANGE_PROFILE_API = "/ERS/api/change";
// URI resource for viewing all employees page
const VIEW_EMPLOYEES_PAGE = "/ERS/employees";
// api call for getting all employees except manager making the request
const ALL_EMP_EXCEPT_ME = "/ERS/api/employees";
// URI for page to approve requests
const APPROVE_REQUESTS_PAGE = "/ERS/approve";
// api call to get all pending requests except specified manager
const GET_ALL_PENDING_EXCEPT_ME = "/ERS/api/view/pending/all";
// api request for approving requests (needs an /id of the reimbursement being approved after)
const APPROVE_REQUEST = "/ERS/api/approvals/approve";
// api request for rejecting requests (needs an /id of the reimbursement being rejected after)
const REJECT_REQUEST = "/ERS/api/approvals/reject";
// URI for page for manager to get all processed requests
const ALL_PROCESSED_PAGE = "/ERS/all-processed";
// URI for api to get all processed requests (for managers)
const ALL_PROCESSED = "/ERS/api/view/processed/all";

// field name for Reimbursement.amount
const REIMB_AMOUNT = 'amount';
// field name for Reimbursement.description
const REIMB_DESCRIPTION = 'description';
// field name for Reimbursement.emplId
const REIMB_EMPLID = 'emplId';
// field name for Reimbursement.reimbDate
const REIMB_REIMBDATE = 'reimbDate';
// field name for Reimbursement.reimbId
const REIMB_REIMBID = 'reimbId';
// field name for Reimbursement.replyDate
const REIMB_REPLYDATE = 'replyDate';
// field name for Reimbursement.status
const REIMB_STATUS = 'status';
// field name for Reimbursement.statusString
const REIMB_STATUSSTRING = 'statusString';
// field name for Reimbursement.submitDate
const REIMB_SUBMITDATE = 'submitDate';
// field name for Reimbursement.manId
const REIMB_MANID = 'manId';
// field name for Reimbursement.managerName
const REIMB_MANAGERNAME = 'managerName';
// field name for Reimbursement.empName
const REIMB_EMPNAME = 'empName';

// field name for Reimbursement.email
const EMPL_EMAIL = 'email';
// field name for Reimbursement.empId
const EMPL_EMPID = 'empId';
// field name for Reimbursement.firstName
const EMPL_FIRSTNAME = 'firstName';
// field name for Reimbursement.isManager
const EMPL_ISMANAGER = 'isManager';
// field name for Reimbursement.lastName
const EMPL_LASTNAME = 'lastName';
// field name for Reimbursement.password
const EMPL_PASSWORD = 'password';
// field name for Reimbursement.token
const EMPL_TOKEN = 'token';
