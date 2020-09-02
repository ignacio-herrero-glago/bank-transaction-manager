Feature: Trancaction manager scenarios
	Scenario: Scenario A
		Given A transaction that is not stored in our system
		When I check the status from channel '<channel>'
		Then The system returns the status 'INVALID'
		Examples:
		  | channel  |
		  | CLIENT   |
		  | ATM      |
		  | INTERNAL |

	Scenario: Scenario B
		Given A transaction that is stored in our system
		And the transaction date is today plus -1 days
		When I check the status from channel '<channel>'
		Then The system returns the status 'SETTLED'
		And the amount substracting the fee
		Examples:
		  | channel |
		  | CLIENT  |
		  | ATM     |

	Scenario: Scenario C
		Given A transaction that is stored in our system
		And the transaction date is today plus -1 days
		When I check the status from channel 'INTERNAL'
		Then The system returns the status 'SETTLED'
		And the amount and the fee

  Scenario: Scenario D
		Given A transaction that is stored in our system
		And the transaction date is today plus 0 days
		When I check the status from channel '<channel>'
		Then The system returns the status 'PENDING'
		And the amount substracting the fee
		Examples:
		  | channel |
		  | CLIENT  |
		  | ATM     |

  Scenario: Scenario E
		Given A transaction that is stored in our system
		And the transaction date is today plus 0 days
		When I check the status from channel 'INTERNAL'
		Then The system returns the status 'PENDING'
    And the amount and the fee

  Scenario: Scenario F
		Given A transaction that is stored in our system
		And the transaction date is today plus 1 days
		When I check the status from channel 'CLIENT'
		Then The system returns the status 'FUTURE'
		And the amount substracting the fee

  Scenario: Scenario G
		Given A transaction that is stored in our system
		And the transaction date is today plus 1 days
		When I check the status from channel 'ATM'
		Then The system returns the status 'PENDING'
		And the amount substracting the fee

  Scenario: Scenario H
		Given A transaction that is stored in our system
		And the transaction date is today plus 1 days
		When I check the status from channel 'INTERNAL'
		Then The system returns the status 'FUTURE'
		And the amount and the fee
