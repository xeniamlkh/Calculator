# What is Calculator App?
Calculator App is **a study project** in Java for Android devices. Please be aware that this project was made for **learning purposes** only and will not be updated or supported further.

This README file has two paragraphs:
1.	[Description](https://github.com/xeniamlkh/Calculator/new/master?filename=README.md#description)
2.	[What was used in this project?](https://github.com/xeniamlkh/Calculator/new/master?filename=README.md#description)

## Description
* Calculator App is **a primitive calculator** that can perform four operations: **addition**, **subtraction**, **multiplication**, and **division**. This description aims to show the structure of the study project, its functionality, and what instruments have been implemented into the project.

  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/c759e873-caf4-4d57-ba5b-b87e945d8907" width="309" height="626">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/a8104edf-6e99-4964-9dc7-2c4e6ab76ee8" width="287" height="624">

* Calculator App can **read user input**, **transform** a string into a mathematical equation, **calculate** it, and return a result to the screen.

* Calculator App works with doubles.

* Calculator App receives a user’s input as a string. However, the app allows the input only in equation form. It means that: 
   - If the equals sign is pressed and the string ends with a plus, minus, multiplication, division, or dot sign, these signs will be removed from the equation. The shown result will be the result of the previous operations or the written variable.
   - If the equals sign is pressed but there is no equation written, the result will be equal to zero.
   -	Writing several arithmetic symbols in a row, one after another, is not allowed. After the sign, a number should be written.
   -	In doubles, only one dot is allowed.
   -	If the string starts with a zero symbol that isn’t followed by the dot sign, the zero symbol will be replaced with the next inserted number.
   -	If there is a division by zero, the result shown is “Infinity”. However, further operations are allowed as if there were no previous calculations.

* Calculator App supports a “Clear All” function through the “C” button.

* Calculator App supports backspace.

* Calculator App recognises arithmetic operators and **performs multiplication and division before addition and subtraction**. The recognition is done by using **Java Regular Expressions**.

  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/3685b53a-2fe5-46cb-bcf5-af44eef095b9" width="299" height="624">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/a3625b65-71b6-4aeb-941a-389f606293c3" width="306" height="624">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/6b254c70-2fbd-416a-a7a1-a09ff92e373b" width="300" height="625">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/c6ede835-f51c-4626-81e4-ac20e4fc6857" width="304" height="625">

* All operations are done in the **ViewModel** class, and the output in the Fragment is updated by using **LiveData**.

* Calculator App supports dark and light themes.

* Calculator App supports a landscape view.

  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/5131616d-c260-45b7-9680-364886ec8f4c" width="646" height="308">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/e1ed680a-8b94-4340-b357-dcd8a71edd04" width="646" height="311">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/dd4160a0-ae77-45e7-8a26-edd2b919db14" width="646" height="314">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/5b7ae23f-67f1-4f97-9d3b-62325b872687" width="646" height="308">

* Calculator App has an icon.
  
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/d23958b7-b68c-41a4-bbe0-a593146a716f" width="298" height="624">
  <img src="https://github.com/xeniamlkh/Calculator/assets/89986215/8b2569d1-f132-40f2-8295-667c2f71eba2" width="307" height="626">

## What was used in this project?

*	Android Architecture
*	ViewModel
*	LiveData
*	View Bindings
*	FragmentManager for displaying a Fragment
*	Constraint Layout, Guidelines, Chains, Bias, - for screen compatibility
*	Landscape View
*	Styles
*	Light and Dark Themes
*	Java Regular Expressions
