Section 1: Starting as Abap Developer {
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
	
	data : lv_x type x. "Any byte values (00 to FF)
	data : lv_n(5) type n. "1 to 65535
	data : lv_c(5) type c. "1 to 65535
	data : lv_d type d. "8 char - date
	data : lv_t type t. "6 char - time
	data : lv_i type i. "Java Integer
	data : lv_f type f . "Float: 2.E-308 to 1.E+308 positive or negative
	
	data : lv_p(16) type p DECIMALS 3. "8 bytes
	data : lv_s type string. "Any alphanumeric characters
	data : lv_xs type xstring. "Any byte values (00 to FF)
	
	data : ls_vbak type vbak. "vbak: table
	data : ls_vbak2 like vbak.
	data : lv_vbeln like vbak-vbeln. "vbeln: column of table 'vbak'
}
}
