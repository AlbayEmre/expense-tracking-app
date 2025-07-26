# 💸 Expense Tracking Application

Kotlin ve Jetpack Compose ile geliştirilen bu Android uygulaması, kullanıcıların harcamalarını hızlıca kaydetmesini, kategori bazlı olarak görüntülemesini ve toplam harcamalarını analiz etmesini sağlar. Uygulama Clean Architecture prensipleriyle katmanlı olarak inşa edilmiştir ve modern Android geliştirme araçlarıyla desteklenmektedir.

---

## 🚀 Özellikler

- ✅ Yeni harcama ekleme / silme
- 📊 Kategori bazlı toplam harcama analizi
- 💵 Toplam harcama gösterimi
- 🧠 MVVM + Clean Architecture mimarisi
- 💾 Room ile yerel veritabanı desteği
- 🔄 Reaktif veri akışı (StateFlow & Flow)
- 🎨 Material 3 temasıyla modern arayüz
- 🔐 Hilt ile bağımlılık enjeksiyonu

---

## 🧱 Proje Mimarisi – Clean Architecture

com.albayemre.expensetrackingapplication
├── data # Veritabanı (Room), Repository impl
│ ├── local
│ │ ├── ExpenseEntity.kt
│ │ ├── ExpenseDao.kt
│ │ ├── ExpenseDatabase.kt
│ │ ├── Converters.kt
│ │ └── ExpenseCategory.kt
│ └── repository
│ ├── ExpenseRepository.kt
│ └── ExpenseRepositoryImpl.kt
│
├── domain # İş kuralları ve UseCase'ler
│ ├── model
│ │ └── Expense.kt
│ └── use_case
│ ├── AddExpense.kt
│ ├── DeleteExpense.kt
│ ├── GetExpenses.kt
│ ├── GetTotal.kt
│ └── GetTotalPerCategory.kt
│
├── presentation # UI, ViewModel, Navigation
│ ├── ui
│ │ ├── list/ExpenseListScreen.kt
│ │ └── addedit/AddEditExpenseScreen.kt
│ ├── nav
│ │ └── ExpenseNavGraph.kt
│ └── viewmodel
│ └── ExpenseViewModel.kt
│
├── di # Hilt modülü
│ └── AppModule.kt
│
├── ExpenseTrackingApplication.kt # Application sınıfı
└── MainActivity.kt


---

## 🛠️ Kullanılan Teknolojiler

| Teknoloji            | Açıklama                                       |
|----------------------|------------------------------------------------|
| **Kotlin**           | Android için modern programlama dili           |
| **Jetpack Compose**  | Declarative UI framework (Material 3 uyumlu)   |
| **Room**             | Yerel veritabanı çözümü                        |
| **Hilt**             | Dependency Injection framework                 |
| **StateFlow / Flow** | Reaktif veri akışı                             |
| **MVVM**             | ViewModel odaklı mimari                        |
| **Clean Architecture** | Katmanlı, bağımsız iş mantığı yapısı       |

---

## 📦 Kurulum ve Çalıştırma

### Gereksinimler

- Android Studio Giraffe veya üstü
- JDK 17
- Minimum SDK: 26
- Compile SDK: 34

### Kurulum Adımları

```bash
git clone https://github.com/kullaniciadi/expense-tracking-application.git
cd expense-tracking-application
