Theme:  GreenCode 
 Title: AST-Powered Sustainable Static Analyzer

*GreenCode** is a specialized static analysis tool designed to identify "Environmental Code Smells" in Java applications. By leveraging **Abstract Syntax Trees (AST)**, it detects inefficient programming patterns that lead to unnecessary CPU cycles, memory churn, and increased carbon footprints.

---

# 🚀 Key Features

- **AST-Based Detection**: Unlike simple regex-based linters, GreenCode builds a full logical model of your code to detect complex nested patterns.
- **Structural Carbon Hotspots**: Interactive graph visualization (using ReactFlow) that maps specific code smells to their structural location in the source file.
- **Green Scoring System**: Automatically calculates a "Sustainability Grade" (A+ to F) based on the severity and frequency of detected smells.
- **CO2 Estimation**: Provides a rough estimate of the carbon impact caused by computational waste in your code.
- **Actionable Refactoring**: Offers specific, high-performance alternatives for every detected issue.

---

## 🛠️ Detected "Green Smells"

GreenCode currently monitors for several key inefficiencies:

| Smell Type | Impact | DSA Concept |
| :--- | :--- | :--- |
| **Nested Loops** | Exponential CPU usage | Algorithmic Complexity $O(n^2)$ |
| **Busy Wait** | 100% CPU utilization while idling | Threading Optimization |
| **String Concat in Loop** | Excessive object allocation | Memory Management |
| **Primitive Boxing** | Heap pressure & GC overhead | Data Structure Choice |
| **Loop Invariants** | Redundant computations | Optimization Heuristics |
| **Unclosed Resources** | OS handle leaks | Resource Lifecycle |
| **Collection Efficiency** | Suboptimal memory/access speed | Data Structures (List/Map) |

---

## 💻 Tech Stack

### Backend
- **Java 21+**
- **Spring Boot 3.3.4**
- **JavaParser**: For Abstract Syntax Tree (AST) generation and traversal.
- **Maven**: Dependency management.

### Frontend
- **React 18**
- **Vite**: Ultra-fast build tool.
- **ReactFlow**: For interactive structural graph visualization.
- **TailwindCSS**: For a modern, "Glassmorphic" UI design.

---

## 🏃 Getting Started

### Prerequisites
- Java Development Kit (JDK) 21 or higher.
- Node.js (v18+) and npm.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/anucodes-hub/GreenCode.git
   cd GreenCode
   ```

2. **Run the Backend**:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
   *The server will start on `http://localhost:8080`*

3. **Run the Frontend**:
   ```bash
   cd ../frontend
   npm install
   npm run dev
   ```
   *The application will be available at `http://localhost:5173`*

---

## 📊 Evaluation Criteria Alignment

- **Relevance**: Directly addresses the growing concern of IT infrastructure's carbon footprint.
- **Innovation**: Uses AST visitors to provide deep structural insights rather than simple surface-level linting.
- **DSA Usage**: Integrates multiple DSA concepts (Trees, DFS Traversal, HashMaps, Algorithmic Complexity) as core functional components.
- **User Experience**: Premium, responsive dashboard with real-time graph rendering.


#📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

*Built with ❤️ for a Greener Digital Future.*

Drive link to live demo video: https://drive.google.com/file/d/16uWe3YZU5EJ6xOhsMqS1SMCXYn2egHPq/view?usp=drivesdk
