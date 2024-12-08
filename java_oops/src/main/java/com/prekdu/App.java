package com.prekdu;

/*
 * Design a Library Management System that handles different types of library resources
(books, digital content, periodicals). The system should demonstrate(Use enums, classes, functions etc):

1. Create an abstract class LibraryResource that contains:
   - Protected fields for resourceId, title, and availabilityStatus
   - Abstract method calculateLateFee(int daysLate)
   - Abstract method getMaxLoanPeriod()

2. Implement different types of resources:
   - Book: Has additional fields for author and ISBN
   - DigitalContent: Has fields for fileSize and format (PDF, EPUB)
   - Periodical: Has fields for issueNumber and frequency (WEEKLY, MONTHLY)

3. Create an interface Renewable with method:
   - boolean renewLoan(LibraryMember member)

4. Create an interface Reservable with methods:
   - void reserve(LibraryMember member)
   - void cancelReservation(LibraryMember member)

5. Implement a LibraryMember class that:
   - Maintains a list of borrowed resources
   - Has a membership type (STANDARD, PREMIUM)
   - Has methods to borrow and return resources

6. Implement proper exception handling for:
   - ResourceNotAvailableException
   - MaximumLoanExceededException
   - InvalidMembershipException

Requirements:
- Books and Periodicals should be both Renewable and Reservable
- DigitalContent should only be Renewable
- Different resource types should have different late fees and loan periods
- Premium members should have higher loan limits and lower late fees and loan periods
- Implement proper validation and business logic

 */
import java.util.ArrayList;
import java.util.List;

// Enums
enum MembershipType {
  STANDARD,
  PREMIUM
}

enum ResourceStatus {
  AVAILABLE,
  BORROWED,
  RESERVED
}

enum ContentFormat {
  PDF,
  EPUB
}

enum Frequency {
  WEEKLY,
  MONTHLY
}

// Abstract Class for Library Resources
abstract class LibraryResource {
  protected String resourceId;
  protected String title;
  protected ResourceStatus availabilityStatus;

  public LibraryResource(String resourceId, String title) {
    this.resourceId = resourceId;
    this.title = title;
    this.availabilityStatus = ResourceStatus.AVAILABLE;
  }

  public ResourceStatus getStatus() {
    return availabilityStatus;
  }

  public void setStatus(ResourceStatus status) {
    this.availabilityStatus = status;
  }

  public abstract double calculateLateFee(int daysLate);

  public abstract int getMaxLoanPeriod();
}

// Interfaces
interface Renewable {
  boolean renewLoan(LibraryMember member);
}

interface Reservable {
  void reserve(LibraryMember member);

  void cancelReservation(LibraryMember member);
}

// Book Class
class Book extends LibraryResource implements Renewable, Reservable {
  private String author;
  private String isbn;
  private LibraryMember reservedBy;

  public Book(String resourceId, String title, String author, String isbn) {
    super(resourceId, title);
    this.author = author;
    this.isbn = isbn;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.5;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 14;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return reservedBy == null || reservedBy == member;
  }

  @Override
  public void reserve(LibraryMember member) {
    if (reservedBy != null || member == null) {
      throw new IllegalStateException("Resource already reserved by another member.");
    }
    reservedBy = member;
    setStatus(ResourceStatus.BORROWED);
  }

  @Override
  public void cancelReservation(LibraryMember member) {
    if (reservedBy != member) {
      throw new IllegalStateException("Resource not reserved by this member.");
    }
    reservedBy = null;
    setStatus(ResourceStatus.AVAILABLE);
  }
}

// Digital Content Class
class DigitalContent extends LibraryResource implements Renewable {
  private double fileSize;
  private ContentFormat format;

  public DigitalContent(String resourceId, String title, double fileSize, ContentFormat format) {
    super(resourceId, title);
    this.fileSize = fileSize;
    this.format = format;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.25;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 7;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return true;
  }
}

// Periodical Class
class Periodical extends LibraryResource implements Renewable, Reservable {
  private int issueNumber;
  private Frequency frequency;
  private LibraryMember reservedBy;

  public Periodical(String resourceId, String title, int issueNumber, Frequency frequency) {
    super(resourceId, title);
    this.issueNumber = issueNumber;
    this.frequency = frequency;
  }

  @Override
  public double calculateLateFee(int daysLate) {
    return daysLate * 0.3;
  }

  @Override
  public int getMaxLoanPeriod() {
    return 7;
  }

  @Override
  public boolean renewLoan(LibraryMember member) {
    return reservedBy == null || reservedBy == member;
  }

  @Override
  public void reserve(LibraryMember member) {
    if (reservedBy != null) {
      throw new IllegalStateException("Resource already reserved by another member.");
    }
    reservedBy = member;
    setStatus(ResourceStatus.BORROWED);
  }

  @Override
  public void cancelReservation(LibraryMember member) {
    if (reservedBy != member && member == null) {
      throw new IllegalStateException("Resource not reserved by this member.");
    }
    reservedBy = null;
    setStatus(ResourceStatus.AVAILABLE);
  }
}

// Library Member Class
class LibraryMember {
  private String memberId;
  private MembershipType membershipType;
  private List<LibraryResource> borrowedResources;

  public LibraryMember(String memberId, MembershipType membershipType) {
    if (membershipType == null) {
      throw new InvalidMembershipException("Membership type cannot be null.");
    }
    this.memberId = memberId;
    this.membershipType = membershipType;
    this.borrowedResources = new ArrayList<>();
  }

  public List<LibraryResource> getBorrowedResources() {
    return borrowedResources;
  }

  public void borrowResource(LibraryResource resource) {
    if (borrowedResources.size() >= getMaxBorrowLimit()) {
      throw new MaximumLoanExceededException(
          "Borrow limit exceeded for " + membershipType + " membership.");
    }
    if (resource.getStatus() != ResourceStatus.AVAILABLE) {
      throw new ResourceNotAvailableException("Resource " + resource.title + " is not available.");
    }
    resource.setStatus(ResourceStatus.BORROWED);
    borrowedResources.add(resource);
  }

  public void returnResource(LibraryResource resource) {
    if (!borrowedResources.contains(resource)) {
      throw new IllegalArgumentException("Resource not borrowed by this member.");
    }
    resource.setStatus(ResourceStatus.AVAILABLE);
    borrowedResources.remove(resource);
  }

  private int getMaxBorrowLimit() {
    return membershipType == MembershipType.STANDARD ? 5 : 10;
  }
}

// Custom Exceptions
class ResourceNotAvailableException extends RuntimeException {
  public ResourceNotAvailableException(String message) {
    super(message);
  }
}

class IllegalStateException extends RuntimeException {
  public IllegalStateException(String message) {
    super(message);
  }
}

class MaximumLoanExceededException extends RuntimeException {
  public MaximumLoanExceededException(String message) {
    super(message);
  }
}

class InvalidMembershipException extends RuntimeException {
  public InvalidMembershipException(String message) {
    super(message);
  }
}
