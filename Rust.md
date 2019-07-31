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
- Constants use the *const* keyword instead of *let* keyword and *data type must be specified*
- Constants can only be *assigned constant expressions* and not to result of functions

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
      1. Access elements by *dot* operator
      2. *Maximum 12* elements
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

```rust
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

```

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

