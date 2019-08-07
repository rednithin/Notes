- [Setup Rust](#setup-rust)
  - [Installing via rustup](#installing-via-rustup)
  - [Creating a new project](#creating-a-new-project)
  - [Adding dependencies](#adding-dependencies)
    - [Install and add to the Cargo.toml file](#install-and-add-to-the-cargotoml-file)
    - [Updating dependencies](#updating-dependencies)
  - [Opening the documentation](#opening-the-documentation)
- [Understanding Rust](#understanding-rust)
  - [Shadowing](#shadowing)
  - [Match Statement, Break and Continue](#match-statement-break-and-continue)
  - [Constants vs Immutable Variables](#constants-vs-immutable-variables)
  - [Data Types](#data-types)
    - [Tuples](#tuples)
    - [Arrays](#arrays)
    - [Functions](#functions)
    - [Comments](#comments)
    - [Control Flow](#control-flow)

# Setup Rust

## Installing via rustup

```bash
pacman -S rustup
rustup toolchain install stable
rustup default stable
rustup component add rls rustfmt rust-src rust-analysis
```

## Creating a new project

```bash
cargo new <project-name> # Folder created
cargo init # In the same folder
```

## Adding dependencies

### Install and add to the Cargo.toml file

```bash
cargo install <package-name>
```

```toml
[package]
name = "rust"
version = "0.1.0"
authors = ["rednithin <reddy.nithinpg@live.com>"]
edition = "2018"

[dependencies]
rand = "0.6.5"
```

### Updating dependencies

Dependency versions are locked (Cargo.lock file) unless you explicitly update with the following command.

```bash
cargo update
```

## Opening the documentation

This builds documentation for all the libraries used.

```bash
cargo doc --open
```

# Understanding Rust

## Shadowing

Can use the same variable name again in the same block.
Spares us from coming up with new names.

```rust
let mut guess = String::new();
io::stdin()
    .read_line(&mut guess)
    .expect("No number entered");

let guess: i32 = guess.trim().parse().expect("Must be a number");
```

## Match Statement, Break and Continue

Break statements are not needed in Rust. However if one is given then it breaks from the enclosing loop.

```rust
loop {
    println!("Guess a number from 1 to 10");

    let mut guess = String::new();
    io::stdin().read_line(&mut guess).expect("No input entered");

    // Handling input errors use continue to continue outer loop
    let guess: i32 = match guess.trim().parse() {
        Ok(num) => num,
        Err(_) => continue,
    };

    println!("Guess: {}, Secret: {}", guess, secret_number);

    // Breaking out of loop can be done directly from inside match
    match guess.cmp(&secret_number) {
        Ordering::Less => println!("Too small"),
        Ordering::Greater => println!("Too big"),
        Ordering::Equal => {
            println!("You Win");
            break;
        }
    }
}
```

## Constants vs Immutable Variables

- Constants can be assigned in global scope
- Constants use the _const_ keyword instead of _let_ keyword and _data type must be specified_
- Constants can only be _assigned constant expressions_ and not to result of functions

## Data Types

Mainly two types of data types

1. Scalar - Single Value
   1. Integers
      1. i8,i16,i32,i64,i128,isize (depends on architecture)
      2. u8,u16,u32,u64,u128,usize (depends on architecture)
   2. Floating Points
      1. f16
      2. f32
   3. Booleans
      1. bool
   4. Characters
      1. char
2. Compound
   1. Tuple
      1. Access elements by _dot_ operator
      2. _Maximum 12_ elements
   2. Arrays

### Tuples

```rust
let tup = (500, 6.4, 1);
let (x, y, z) = tup; // Destructuring
```

### Arrays

```rust
let a = [1, 2, 3, 4, 5];
let first = a[0];
let second = a[1];
```

### Functions

```rust
fn another_function(x: i32) {
    println!("The value of x is: {}", x);
}

fn main() {
    let x = (let y = 6); // Error Statements don't return values
}

fn plus_one(x: i32) -> i32 { // Functions with return values
    x + 1;
}

```

### Comments

```rust
// Single Line comments
/*
    Multi
    Line
    Comments
*/
```

### Control Flow

````rust
if a < b {

} else if b < c {

} else {

}
```rust
let number = if condition { // Ternary Operator
    5
} else {
    6
};

````

### Loops

```rust
for number in (1..4).rev() {
    println!("{}!", number);
}
```

```rust
let a = [10, 20, 30, 40, 50];
for element in a.iter() {
    println!("the value is: {}", element);
}
```

```rust
let a = [10, 20, 30, 40, 50];
let mut index = 0;
while index < 5 {
    println!("the value is: {}", a[index]);
    index = index + 1;
}
```

```rust
loop {
    println!("again!");
}
```

```rust
// Break and return a value to be used
let mut i = 1;
let result = loop {
    i += 1;
    if i == 5 {
        break "Hello";
    }
};
println!("{}", result);
```

## Ownership

### Understanding scopes

```rust
{                      // s is not valid here, it’s not yet declared
    let s = "hello";   // s is valid from this point forward

    // do stuff with s
}                      // this scope is now over, and s is no longer valid
```

```rust
let mut s = String::from("hello");

s.push_str(", world!"); // push_str() appends a literal to a String

println!("{}", s); // This will print `hello, world!`
```

There is a natural point at which we can return the memory our String needs to the operating system: when s goes out of scope. When a variable goes out of scope, Rust calls a special function for us. This function is called drop, and it’s where the author of String can put the code to return the memory. Rust calls drop automatically at the closing curly bracket.

> Note: In C++, this pattern of deallocating resources at the end of an item’s lifetime is sometimes called Resource Acquisition Is Initialization (RAII). The drop function in Rust will be familiar to you if you’ve used RAII patterns.

> When two pointers free the same location

```rust
// Clone when type is in heap
let s1 = String::from("hello");
let s2 = s1.clone();

println!("s1 = {}, s2 = {}", s1, s2);
```

```rust
// No need to clone for data types in stack
let x = 5;
let y = x;

println!("x = {}, y = {}", x, y);
```

> Rust has a special annotation called the Copy trait that we can place on types like integers that are stored on the stack (we’ll talk more about traits in Chapter 10). If a type has the Copy trait, an older variable is still usable after assignment. Rust won’t let us annotate a type with the Copy trait if the type, or any of its parts, has implemented the Drop trait. If the type needs something special to happen when the value goes out of scope and we add the Copy annotation to that type, we’ll get a compile-time error.

```rust
// Take ownership and give it back
fn main() {
    let s1 = gives_ownership();         // gives_ownership moves its return
                                        // value into s1

    let s2 = String::from("hello");     // s2 comes into scope

    let s3 = takes_and_gives_back(s2);  // s2 is moved into
                                        // takes_and_gives_back, which also
                                        // moves its return value into s3
} // Here, s3 goes out of scope and is dropped. s2 goes out of scope but was
  // moved, so nothing happens. s1 goes out of scope and is dropped.

fn gives_ownership() -> String {             // gives_ownership will move its
                                             // return value into the function
                                             // that calls it

    let some_string = String::from("hello"); // some_string comes into scope

    some_string                              // some_string is returned and
                                             // moves out to the calling
                                             // function
}

// takes_and_gives_back will take a String and return one
fn takes_and_gives_back(a_string: String) -> String { // a_string comes into
                                                      // scope

    a_string  // a_string is returned and moves out to the calling function
}
```

## Borrowing - (References)

There are two types of references

- Immutable References
- Mutable reference - only one

```rust
// Immutable references
fn main() {
    let s1 = String::from("hello");

    let len = calculate_length(&s1);

    println!("The length of '{}' is {}.", s1, len);
}

fn calculate_length(s: &String) -> usize {
    s.len()
}
```

```rust
// Mutable references
fn main() {
    let mut s = String::from("hello");

    change(&mut s);
}

fn change(some_string: &mut String) {
    some_string.push_str(", world");
}
```

The benefit of having only one mutable reference is that Rust can prevent data races at compile time. A data race is similar to a race condition and happens when these three behaviors occur:

- Two or more pointers access the same data at the same time.
- At least one of the pointers is being used to write to the data.
- There’s no mechanism being used to synchronize access to the data.

```rust
// As always, we can use curly brackets to create a new scope, allowing for multiple mutable references, just not simultaneous ones
let mut s = String::from("hello");

{
    let r1 = &mut s;

} // r1 goes out of scope here, so we can make a new reference with no problems.

let r2 = &mut s;
```

```rust
// Cannot have immutable and mutable refernces
let mut s = String::from("hello");

let r1 = &s; // no problem
let r2 = &s; // no problem
let r3 = &mut s; // BIG PROBLEM

println!("{}, {}, and {}", r1, r2, r3);
```

```rust
// BUT HOLD ON. One can create a mutable reference after the last usage of all immutable references
let mut s = String::from("hello");

let r1 = &s; // no problem
let r2 = &s; // no problem
println!("{} and {}", r1, r2);
// r1 and r2 are no longer used after this point

let r3 = &mut s; // no problem
println!("{}", r3);
```

### Dangling Pointers

```rust
fn dangle() -> &String { // dangle returns a reference to a String

    let s = String::from("hello"); // s is a new String

    &s // we return a reference to the String, s
} // Here, s goes out of scope, and is dropped. Its memory goes away.
  // Danger!
```

```rust
fn no_dangle() -> String {
    let s = String::from("hello");

    s
}
```

### Slices

> Another data type that does not have ownership is the slice. Slices let you reference a contiguous sequence of elements in a collection rather than the whole collection.

```rust
fn first_word(s: &String) -> &str { // Return type of string slice &str and array slice is &[i32]
    let bytes = s.as_bytes();
    for (i, &c)  in bytes.iter().enumerate() {
        if c == b' ' {
            return &s[..i];
        }
    }
    &s
}

fn main() {
    let s = String::from("Hello World");
    let first =  first_word(&s);
    print!("{}", first);
}
```

## Structs

### Creation

```rust
struct User {
    username: String,
    email: String,
    sign_in_count: u64,
    active: bool,
}
```

### Instantiation

```rust
let user1 = User {
    email: String::from("someone@example.com"),
    username: String::from("someusername123"),
    active: true,
    sign_in_count: 1,
};
```
### Changing

```rust
let mut user1 = User {
    email: String::from("someone@example.com"),
    username: String::from("someusername123"),
    active: true,
    sign_in_count: 1,
};

user1.email = String::from("anotheremail@example.com");
```

### JS similarity 

```rust
fn build_user(email: String, username: String) -> User {
    User {
        email: email,
        username: username,
        active: true,
        sign_in_count: 1,
    }
}
```
OR SHORTHAND
```rust
fn build_user(email: String, username: String) -> User {
    User {
        email,
        username,
        active: true,
        sign_in_count: 1,
    }
}
```

```rust
let user2 = User {
    email: String::from("another@example.com"),
    username: String::from("anotherusername567"),
    active: user1.active,
    sign_in_count: user1.sign_in_count,
};
```
OR SHORTHAND
```rust
let user2 = User {
    email: String::from("another@example.com"),
    username: String::from("anotherusername567"),
    ..user1
};
```

### Tuple structs without named fields

```rust
struct Color(i32, i32, i32);
struct Point(i32, i32, i32);

let black = Color(0, 0, 0);
let origin = Point(0, 0, 0);
```

> In the User struct definition in Listing 5-1, we used the owned String type rather than the &str string slice type. This is a deliberate choice because we want instances of this struct to own all of its data and for that data to be valid for as long as the entire struct is valid. (Requires lifetimes knowledge)

> VISIT EXAMPLE PROGRAM TO CALC AREA OF RECTANGLE USING TUPLES / STRUCTS

> Putting the specifier :? inside the curly brackets tells println! we want to use an output format called Debug

```rust
// To print structs use the debug placeholder and derive the debug.
#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

fn main() {
    let rect1 = Rectangle { width: 30, height: 50 };
    println!("rect1 is {:?}", rect1);
    println!("rect1 is {:#?}", rect1); // Pretty Printing
}
```