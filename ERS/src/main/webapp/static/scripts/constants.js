/**
 * This file holds constants that help keep things centralized between my java
 * code and my javascript code.
 */

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
// value signaling a success (value of SUCCESS_COKIR)
const SUCCESS_VALUE = "success";
// URI of view reimbursement resource
const VIEW_REIMBURSEMENT_PAGE = "/ERS/view";
// URI for returning all reimbursements for an employee
const VIEW_ALL_REIMBURSEMENTS_BY_EMP = "/ERS/api/view";// field name for Reimbursement.amount
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
