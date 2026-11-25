# AI Collaboration Log (`ai_log`)

**Project:** Object-Oriented RPG Character & Equipment System (Java)  
**Date:** November 25, 2026

---

## 1. Initial System Design & Architecture
* **Prompt / Objective:** ออกแบบโครงสร้างคลาสสำหรับตัวละคร RPG (`RPGCharacter`) และอุปกรณ์สวมใส่ (`Equipment`, `Sword`, `Shield`) ที่รองรับการอัปเลเวล การคำนวณค่าพลังโจมตี/ป้องกัน และการลดความเร็วในการวิ่ง
* **AI Output & Key Concepts:**
    * แยกความรับผิดชอบด้วยหลัก **Inheritance** (`Equipment` เป็น Abstract Superclass ของ `Sword` และ `Shield`) และ **Composition** (`RPGCharacter` มี Attribute เป็น `Sword` และ `Shield`)
    * แนะนำการใช้ **Dynamic Stat Calculation** (คำนวณค่าพลังใน Getter Method แทนการเก็บค่าตายตัว) เพื่อให้ค่าสถานะอัปเดตอัตโนมัติเมื่อมีการสวมใส่ ถอด หรืออัปเลเวล

## 2. Deep Copy Implementation
* **Prompt / Objective:** ทำความเข้าใจแนวคิด Deep Copy และออกแบบคลาส `Equipment` ให้รองรับการคัดลอก Object อย่างสมบูรณ์
* **AI Output & Key Concepts:**
    * อธิบายความแตกต่างระหว่าง **Shallow Copy** (คัดลอกแค่ Reference ทำให้อุปกรณ์ของตัวต้นฉบับและตัวก๊อปปี้เชื่อมกัน) กับ **Deep Copy** (สร้าง Instance ใหม่ทั้งหมดแยกขาดจากกันใน Memory)
    * ออกแบบ **Copy Constructor** (`protected Equipment(Equipment other)`) และ Polymorphic `copy()` method เพื่อป้องกันปัญหาการแชร์ Reference ของอุปกรณ์ระหว่างตัวละคร

## 3. Code Refactoring & Reference Synchronization
* **Prompt / Objective:** แก้ปัญหาเมื่อสั่ง `sword.levelUP()` แล้วค่าพลังโจมตีและความเร็ววิ่งของ `RPGCharacter` ไม่เปลี่ยนแปลงตาม
* **Root Cause Identified:** โค้ดเดิมบวกค่าพลังของดาบเข้าไปในตัวแปร `damage` ของตัวละครแบบตายตัว (Cached State) ตอนเรียก `equipWeapon()` ทำให้ไม่รับรู้การเปลี่ยนแปลงเมื่อดาบอัปเลเวลในภายหลัง
* **Resolution:** ปรับเมธอด `getDamage()`, `getDefense()`, `getRunSpeed()` และ `getMaxRunSpeed()` ให้ดึงค่าล่าสุดจาก `sword` และ `shield` มาคำนวณร่วมกับ Base Stats ทุกครั้งที่เรียกใช้

## 4. Production Architecture Discussion
* **Prompt / Objective:** เปรียบเทียบแนวทางการเก็บข้อมูลระหว่างโปรเจกต์ OOP กับการพัฒนาเกมจริง (Game Production)
* **Key Takeaways:** สรุปข้อจำกัดของ Dynamic Getter เมื่อมีบัฟซับซ้อน และเปรียบเทียบสถาปัตยกรรมที่ใช้ในเกมจริง ได้แก่ **Dirty Flag Pattern** (Caching), **Stat & Modifier System** (แยกค่าพลังและบัฟเป็น Object), และ **Data-Driven Design** (แยกค่าตัวเลขลงไฟล์ JSON/CSV)

## 5. Code Review & Bug Fixing (Combat & Equipment Methods)
* **Prompt / Objective:** ตรวจสอบความถูกต้องของเมธอด `attack()`, `slash()`, `getAttacked()` และ `unequipWeapon()` ในเวอร์ชันปรับปรุง
* **Issues Found & Fixed:**
    * ป้องกัน `NullPointerException` ใน `attack()` โดยเปลี่ยนจาก `sword.getDamage()` เป็น `getDamage()`
    * แก้ไขเงื่อนไขตรวจสอบ Mana ใน `slash()` จาก `mn < 10` เป็น `mn >= 10`
    * แก้ไข `getAttacked()` ให้ตรวจสอบเงื่อนไขการตายจาก `hp <= 0` แทน `mn <= 0` และใช้ `getDefense()` เพื่อรวมค่าป้องกันพื้นฐานของตัวละคร

## 6. Test Suite Generation (`Main.java`)
* **Prompt / Objective:** สร้างชุดทดสอบจำลองการต่อสู้ระหว่างตัวละคร 2 ตัว (`Arthur` vs `Lancelot`) ให้ครอบคลุมทุกเมธอด
* **Test Coverage:** ทดสอบการสร้างตัวละคร, การสวมใส่/ถอดอุปกรณ์ (`equipWeapon`, `unequipWeapon`), การโจมตีปกติและใช้สกิล (`attack`, `slash`, `getAttacked`), การอัปเลเวลตัวละครและอาวุธระหว่างต่อสู้ (`levelUP`), และการแสดงผลสถานะ (`charStatus`)