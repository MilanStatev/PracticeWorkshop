# OOP Workshop - Furniture

## Preface

Before you start coding, read this document from top to bottom. It has some valuable information that will make your work easier.

## Description

A furniture manufacturer keeps track of the companies they work with and the furniture they are manufacturing. 

The furniture pieces are tables and different types of chairs. The companies have a collection of their furniture which they can list in a catalog.

Your task is to design an object-oriented class hierarchy to model the Furniture Factory, using the best practices for object-oriented design (OOD) and object-oriented programming (OOP). Avoid duplicated code through abstraction, inheritance, and polymorphism, and encapsulate all fields correctly.

## Architecture

The Furniture workshop already has a working Engine. **You do not have to touch anything in it.** Just use it.

You are given all Java interfaces you need to build an adequate interface hierarchy and use as a basis for your code.

## Models

> Note: All validation intervals are inclusive (closed).

### **Table**

- `model code` - a string that cannot be `null` or with a length less than `3` symbols.
- `material type` - one of `[Wooden, Leather or Plastic]`.
- `price` - a number greater than `0`.
- `height` - a number greater than `0`.
- `length` - a number greater than `0`.
- `width` - a number greater than `0`.
- `area` - calculated by the formula `length * width`.
- Should be convertible to **String** in the format:

```none
Type: Table, Model Code: {modelCode}, Material: {material}, Price: {price}, Height: {height}, Length: {length}, Width: {width}, Area: {area}
```

### **Chair**
- `model code` - a string that cannot be `null` or with a length less than `3` symbols.
- `material type` - one of `[Wooden, Leather or Plastic]`.
- `price` - a number greater than `0`.
- `height` - a number greater than `0`.
- `number of legs` - a number greater than `0`.
- Chairs can be one of three types: `[Regular, Adjustable and Convertible]`.
  - Each `Adjustable chair` can adjust its `height`.
  - Each `Convertible chair` has two states – `Converted` or `Normal`.
	- If the state is `Converted`, the `height` of the chair is `0.10`.
	- If the state is `Normal`, the `height` is the initial one.
	- When initialized, the state is `Normal`.
- Should be convertible to **String** in the format:

#### **Regular and Adjustable Chairs**

```none
Type: {Chair or Adjustable Chair}, Model Code: {modelCode}, Material: {material}, Price: {price}, Height: {height}, Legs: {legsCount}
```

#### **Convertible Chair**

```none
Type: Convertible Chair, Model Code: {modelCode}, Material: {material}, Price: {price}, Height: {height}, Legs: {legsCount}, State: {isConverted ? Converted: Normal}
```

### **Company**

- `name` - a string that cannot be `null` or with length less than `5` symbols.
- `registration number` - must be exactly `10` symbols and must contain only digits.
- cannot have two pieces of furniture with the same `model code`.
- can **add** or **remove** furniture from catalogs.
- can find furniture from their list of furniture by `model code`. 
- `getCatalog()` method - returns the information about the available furniture **ordered by price then by model** (if there are any added).
- Should be convertible to **String**. If the company has no furniture added, print "No furniture".
  - If the company has furniture, print the information about each piece on a separate line (see the example below and the sample output).

```
Catalog of {Company name} {(registration number)}: {No furniture (if empty)}
  - {Information about the piece of furniture (if not empty)}
  - {Information about the piece of furniture (if not empty)}
  - {Information about the piece of furniture (if not empty)}
  ...
```

### **Additional Validations**
- All double type fields should be printed rounded to two decimal places.

### Commands

Currently, the engine supports the following commands:

- **CreateCompany** `[company name] [registration number]` – creates a company.
- **AddFurnitureToCompany** `[company name] [furniture model]` – adds furniture to a company’s catalog. 
- **RemoveFurnitureFromCompany** `[company name] [furniture model]` –  removes furniture from a company’s catalog. 
- **FindFurnitureFromCompany** `[company name] [furniture model]` – searches for furniture in a company’s catalog. 
- **ShowCompanyCatalog** `[company name]` – invokes a company `getCatalog()` method.
- **SetChairHeight** `[model code] [height]` – sets chair height. 
- **ConvertChair** `[model code]` – converts chair state.

In case of an invalid operation or error, the engine returns appropriate text messages.

**Commands that you should implement yourself:**

*Hint: Study the already implemented commands to understand how to do it.*

- **CreateTable** `[model code] [material] [price] [height] [length] [width]` - creates a table.

- **CreateChair** `[model code] [material] [price] [height] [legs] [type]` - creates a chair.

> *Note:* All commands should return appropriate success messages. The relevant error message is printed in case of an invalid operation or error.

## Unit Tests

- You have been given unit tests to keep track of your progress. Run them by right-clicking the **tests** package and selecting **Run 'All Tests'**.
- If you get stuck, check out the tests' names to guide you on what to do.
 
> Note: Be careful not to change anything in the **tests** folder.

## Step by step guide

> *Note:* The code will not compile successfully at first. Stay calm and determine what information the errors can give you.

1. Build the interface hierarchy

   - Look at the **contracts** package and decide how to connect the interfaces.

2. Build the class hierarchy

   - Can any common fields/behavior be combined in a base class?
   - Do you have repeating code in more than one class?

3. Implement `FurnitureRepositoryImpl`

   - Look at the `getFurniture()` method. It must return a `List` of `Furniture`. Can we use the created list (*hint: Polymorphism!*)?
   - Look at the `findElementByModelCode()` method. How can we use it?

4. Add printing

   - You need to override the `toString()` method in each class. Follow the format specified above.

5. Validate all properties according to the guidelines specified above

   - Take a look at what's in the `ValidationHelpers` class. Is there something that can be useful?
   - If necessary, you can add more validation methods in `ValidationHelpers`

6. Implement the methods inside `CompanyImpl`

   - Remember what you need to do to make a class sortable?
   - Consider using the `Comparable` interface?

7. Implement the `CreateTableCommand`

8. Finish the `CreateChairCommand`

### Sample Input

```none
CreateCompany CompanyFurniture 1337855391
ShowCompanyCatalog CompanyFurniture
CreateTable EQ1923 wooden 123.4 0.50 0.45 0.65
CreateChair HG1124 leather 99.99 1.20 5 Regular
CreateChair LY8212 leather 111.56 0.80 4 Adjustable
CreateChair GG4201 plastic 80.00 1.00 3 Convertible
ShowCompanyCatalog CompanyFurniture
AddFurnitureToCompany CompanyFurniture EQ1923
AddFurnitureToCompany CompanyFurniture HG1124
AddFurnitureToCompany CompanyFurniture LY8212
AddFurnitureToCompany CompanyFurniture GG4201
ShowCompanyCatalog CompanyFurniture
RemoveFurnitureFromCompany CompanyFurniture HT9172
ShowCompanyCatalog CompanyFurniture
FindFurnitureFromCompany CompanyFurniture GG4201
ConvertChair GG4201
FindFurnitureFromCompany CompanyFurniture LY8212
RemoveFurnitureFromCompany CompanyFurniture HG1124
RemoveFurnitureFromCompany CompanyFurniture EQ1923
ShowCompanyCatalog CompanyFurniture
Exit
```

> *Hint:* Don't use the whole input as a test. Break it as simply as possible. One line at a time is a good starting point.

### Sample Output

```none
Company CompanyFurniture created.
Catalog of CompanyFurniture (1337855391): No furniture.
Table with model code EQ1923 created.
Chair with model code HG1124 created.
Chair with model code LY8212 created.
Chair with model code GG4201 created.
Catalog of CompanyFurniture (1337855391): No furniture.
EQ1923 added to CompanyFurniture.
HG1124 added to CompanyFurniture.
LY8212 added to CompanyFurniture.
GG4201 added to CompanyFurniture.
Catalog of CompanyFurniture (1337855391):
  - Type: Convertible Chair, Model Code: GG4201, Material: Plastic, Price: 80.00, Height: 1.00, Legs: 3, State: Normal
  - Type: Chair, Model Code: HG1124, Material: Leather, Price: 99.99, Height: 1.20, Legs: 5
  - Type: Adjustable Chair, Model Code: LY8212, Material: Leather, Price: 111.56, Height: 0.80, Legs: 4
  - Type: Table, Model Code: EQ1923, Material: Wooden, Price: 123.40, Height: 0.50, Length: 0.45, Width: 0.65, Area: 0.29
Furniture with model code HT9172 does not exist.
Catalog of CompanyFurniture (1337855391):
  - Type: Convertible Chair, Model Code: GG4201, Material: Plastic, Price: 80.00, Height: 1.00, Legs: 3, State: Normal
  - Type: Chair, Model Code: HG1124, Material: Leather, Price: 99.99, Height: 1.20, Legs: 5
  - Type: Adjustable Chair, Model Code: LY8212, Material: Leather, Price: 111.56, Height: 0.80, Legs: 4
  - Type: Table, Model Code: EQ1923, Material: Wooden, Price: 123.40, Height: 0.50, Length: 0.45, Width: 0.65, Area: 0.29
Type: Convertible Chair, Model Code: GG4201, Material: Plastic, Price: 80.00, Height: 1.00, Legs: 3, State: Normal
Chair GG4201's state switched from normal to converted.
Type: Adjustable Chair, Model Code: LY8212, Material: Leather, Price: 111.56, Height: 0.80, Legs: 4
HG1124 removed from CompanyFurniture.
EQ1923 removed from CompanyFurniture.
Catalog of CompanyFurniture (1337855391):
  - Type: Convertible Chair, Model Code: GG4201, Material: Plastic, Price: 80.00, Height: 0.10, Legs: 3, State: Converted
  - Type: Adjustable Chair, Model Code: LY8212, Material: Leather, Price: 111.56, Height: 0.80, Legs: 4
```
