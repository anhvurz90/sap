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
	Se21: Package Builder
	
	Sm30: Maintain Table Views: Initial Screen
	/Nse38:  
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
Section 2: Abap Dictionary {
	00.Definition: {
		- ABAP Dictionary is a central repository where we DEFINE and MAINTAIN the objects
		which are related to database.
		- ABAP Dictionary provides the following object TYPEs: {
			+ Database table - Creates table definitions in ABAP Dict, 
							independent of any database.
			+ View - Create view definitions in ABAP Dictionary. A view is a 
							virtual table that does not store the data physically.
			+ Data type - Creates a definition of a user-defined type in ABAP Dic:
							+ Data Element
							+ Structure
							+ Table type
			+ Type group - Create groups of data types in ABAP Dic.
			+ Domain - Create domains in ABAP Dic. A domain is used to describe 
						the technical attributes of a field, such as a range of values.
			+ Search help - Creates a help document (F4 Help) or called input help for fields.
			+ Lock object - Creates a local or lock object that helps synchronize 
						the access of multiple users simultaneously.
		}
	}
}
Section 3: Handling Data: {
	3.1.Using Select-Endselect {
		REPORT ZABAP101_HELLO_WORLD.
		tables : ZFI_T_PAYMENT.
		START-OF-SELECTION.
		
		select * FROM ZFI_T_PAYMENT.
			write : / ZFI_T_PAYMENT-belnr, ZFI_T_PAYMENT-dmbtr,
					ZFI_T_PAYMENT-waers.
		endSelect.
	}
	3.2.Internal Tables & Work Areas: {
		- Internal Table: {
			+ An Internal Table is a temporary table that contains the records
			of an ABAP program while it is being executed.
			+ An Internal Table exists only during the runtime of an SAP program
			+ An IT is declared in an ABAP program when you need to retrieve data 
			from database tables.
			+ IT is stored in rows and columns.
			+ The individual records of an IT are accessed with an index or a key.
			+ The records of the IT are discarded when the execution of the program
			is terminated.
		}
	}
	3.3.Working with Internal Tables & Loop: {
		data : it_payment like TABLE OF zfi_t_payment. " Internal Table
		data : wa_payment type zfi_t_payment.  " Work Area
		data : it_payment2 like TABLE OF zfi_t_payment WITH HEADER LINE.
		 
		SELECT * FROM zfi_t_payment
			INTO TABLE it_payment
		SELECT * FROM zfi_t_payment
			INTO TABLE it_payment2
		
		LOOP AT it_payment INTO wa_payment.
			WRITE : / wa_payment-belnr, wa_payment-dmbtr,
						wa_payment-waers.
		ENDLOOP
	}
	3.4.Internal Tables - Append & Collect: {
		wa_payment-belnr = "6534".
		wa_payment-dmbtr = 120.
		wa_payment-waers = 'USD'.
		
		APPEND wa_payment TO it_payment.
		COLLECT wa_payment INTO it_payment.
	}
	3.5.Internal Tables - Modify, Insert & Delete: {
		- Modify: {
			LOOP AT it_payment INTO wa_payment.
				wa_payment-dmbtr = 150.
				wa_payment-belnr = '75654'.
				MODIFY it_payment FROM wa_payment INDEX sy-tabix
						TRANSPORTING dmbtr. 
				"belnr changes in WA only, but
				"dmbtr changes in both WA and IT
			ENDLOOP.
		}
		- Insert: {
			wa_payment-belnr = '6534'.
			wa_payment-dmbtr = 120.
			wa_payment-waers = 'USD'.
			
			INSERT wa_payment INTO it_payment INDEX 2.
		}
		- Delete: {
			DELETE it_payment INDEX 2.
		}
	}
	3.6.Using 'Read Table' statement: { "Read 1 row into work area
		DATA : lv_belnr LIKE zfi_t_payment-belnr.
		lv_belnr = '0000000124'.
		
		- READ TABLE it_payment INTO wa_payment INDEX 2.
		- READ TABLE it_payment INTO wa_payment WITH KEY belnr=lv_belnr.
	}
}
Section 4: Selection Screens: {
	4.0.Definition: {
		- A Selection Screen is one of the four types of user dialogs:
		- The selection screens are designed whenever a user wants to
			design a screen meant only for accepting data input
		- Both Single value input or Complex value input can be provided 
			using Selection screen. 
		- The single value entered in a field is used primarily 
			to control the flow of a program.
		- The complex criteria, on the other hand, are used 
			to restrict the amount of data read from the SAP database.(filter)
		- The selection screen can be defined with ABAP statements.
		- For designing the Selection Screen, we have the following Statements:
			+ Parameters
			+ Select - Options
			+ Selection-Screen Command.
	}
	4.1.Create your first SelectionScreen: {
		TABLES : zfi_t_payment.

		SELECTION-SCREEN begin of block 1 WITH FRAME TITLE text-001.
			PARAMETERS : p_belnr TYPE zfi_t_payment-belnr.
			SELECT-OPTIONS s_datum for zfi_t_payment-datum DEFAULT sy-datum.
			PARAMETERS : p_chk AS CHECKBOX.
			PARAMETERS : 	r1 RADIOBUTTON GROUP rg DEFAULT 'x',
							r2 RADIOBUTTON GROUP rg
		SELECTION-SCREEN end of block 1.
	}
	4.2.Additions to your Selection Screen: {
		- sy-datum NO-EXTENSION:
			+ SELECT-OPTIONS s_datum for zfi_t_payment-datum DEFAULT sy-datum
				NO-EXTENSION.
		- sy-datum NO INTERVALS:
			+SELECT-OPTIONS s_datum for zfi_t_payment-datum DEFAULT sy-datum
				NO INTERVALS.
		- param OBLIGATORY:
			PARAMETERS : p_belnr type zfi_t_payment-belnr OBLIGATORY.
		- param MEMORY ID:
			PARAMETERS : p_wrk type t001w-werks MEMORY ID wrk.
		- comment:
			SELECTION-SCREEN : begin of line.
				SELECTION-SCREEN  COMMENT (11) text-002 FOR FIELD p_belnr.
				PARAMETERS : p_belnr TYPE zfi_t_payment-belnr.
			SELECTION-SCREEN : end of line.
	}
	4.3.Selection Screen Events: {
		- parameters : p_wrk type t001w-werks memory id wrk MODIF ID 123		
		   parameters : r1 radiobutton group rg default 'x' USER-COMMAND,
					    r2 radiobutton group rg.
		
		- pbo: Process Before Output
			AT SELECTION-SCREEN output.
				BREAK-POINT.
				LOOP AT screen.
					IF rx = 'X'.
					IF screen-group1 = '123'.
						screen-active = 0.
					ENDIF.
					MODIFY SCREEN.
					ENDIF.
				ENDLOOP.

		- pai: Process After Input
			AT SELECTION-SCREEN
				BREAK-POINT.
		
		- INITIALIZATION.
		- BREAK-POINT.
		
		- AT SELECTION-SCREEN on VALUE-REQUEST FOR  s_datum-low.
			break-point.
		- AT SELECTION-SCREEN on RADIOBUTTON GROUP rg.
			break-point.
	}
}