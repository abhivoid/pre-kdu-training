/*
  Define a generic function filterArray<T>(arr: T[], predicate: (item: T) => boolean): T[] that filters an array based on a predicate function.
  Use this function to filter an array of numbers and return only even numbers.
  Use the same function to filter an array of User objects and return users whose email includes "@company.com".

  Export the filterArray function so that the code can be tested in the test file.
*/
import { User } from "./q1";

export function filterArray<T>(arr: T[], predicate: (item: T) => boolean): T[]{
  return arr.filter(predicate);
}

//Using the function to get even numbers
const numbers=[1,22,43,66,23,12];
const evenNumbers=filterArray(numbers, (val) => val%2 === 0);
console.log(evenNumbers);

//Using the fucntion to filter users with email including @company.com
const users: User[] = [
  { id: 1, name: "Abhishek", email: "abhi@company.com", role: "admin" },
  { id: 2, name: "Bilu", email: "bilu@gmail.com", role: undefined },
  { id: 3, name: "Chintu", email: "chintu@company.com", role: "user" },
];

const companyUsers=filterArray(users, (user) => user.email.includes("@company.com"));
console.log(companyUsers);