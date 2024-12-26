/*
  Create a utility function findById that takes an array of User objects and a number as arguments and returns a User | undefined (if the user with the given ID exists).
  Extend the function to support a new parameter, which specifies if the returned user should include undefined or throw an error. Use TypeScript's never type to represent the error-throwing case.
  Test the function using a sample array of User objects and handle both scenarios (with and without throwing an error).

  Export the function findById so that it can be used in the test file.
*/

import { User } from "./q1";

export function findById(
  users: User[],
  id: number,
  flag: boolean=false
): User | undefined {
  const user=users.find((u)=>u.id===id);

  if (!user && flag) {
    throw new Error(`User with ID ${id} not found.`);
  }
  
  return user;
}

const users: User[] = [
  { id: 1, name: "Abhishek", email: "abhi@company.com", role: "admin" },
  { id: 2, name: "Bilu", email: "bilu@gmail.com", role: undefined },
  { id: 3, name: "Chintu", email: "chintu@company.com", role: "user" },
];

// 1. Find a user by ID (without throwing an error)
try {
  const foundUser = findById(users, 1); // ID exists
  console.log(foundUser); 
} catch (err) {
  console.error(err);
}

// 2. Find a user by ID (with throwing an error)
try {
  const foundUser = findById(users, 4, true); // ID does not exist, flag = true
  console.log(foundUser);
} catch (err) {
  console.error(err); // Output: Error: User with ID 4 not found.
}

// 3. Find a user by ID (without throwing an error, user not found)
const userNotFound = findById(users, 4); // ID does not exist
console.log(userNotFound); // Output: undefined