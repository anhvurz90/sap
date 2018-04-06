01.Tcode: {
	Se80: Abab Workbench
	Se38: Abap Programs
	Se37: Function Modules
	Se24: Class Builder
	Se11: Abap Dictionary
	Se91: Message Maintenance
	Se93: Transactions
	Se10: Transport Organizer
}
02.Code editor: {
	Editor: Alt+Shift+F1
	Activate: Alt+Shift+F3
	Execute: F8
	Pretty Print: Shift+F1
}
03.Code: {
	--------------------
START-OF-SELECTION.
	write 'Hello World!'.
	data: text type string value '0123456789ABCDEF',
		  col type i value 25,
		  len type i value 5.
	write / text.
	write /5(10) text.
	write /col(len) text.
	write / sy-datum.

	data : date_short type c length 8,
		date_long type c length 10,
		date_mask(8) type c.
	
	write sy-datum to date_short.
	write sy-datum to : date_long,
					  date_mask DD/MM/YY.
	write: /5(10) date_short, date_long.
	write: date_mask.
	
	write: / text intensified off.
	write: / text color col_normal.
	write: / text color col_total.
	write: / text hotspot.
}
04.Data Types: {
}
