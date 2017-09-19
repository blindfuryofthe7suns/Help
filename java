<script language="JavaScript" type="text/javascript">

var WAArray = new Array();
var NONArray = new Array();
var t1018Array = new Array();
var creditArray = new Array();
var qtrArray = new Array(3);
var froomArray = new Array();
var wroomArray = new Array();
var sroomArray = new Array();
var courseArray = new Array();
var bookArray = new Array();
var miscArray = new Array();
var parkArray = new Array();
var transArray = new Array();
var feesArray = new Array();

/* per credit fee WA resident ug<10; ug>18; g<10; g>18 */
temp_string = "208;208;309;309";
WAArray = temp_string.split(';');

/* per credit fee NON-WA resident ug<10; ug>18; g<10; g>18 */
temp_string = "692;692;664;664";
NONArray = temp_string.split(';');

/* tuition rates for 10 <= credits >= 18, WA-ug; NON-WA-ug; WA-g; NON-WA-g */
temp_string = "2083;6920;3655;7203";
t1018Array = temp_string.split(';');

temp_string = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25";
creditArray = temp_string.split(',');

/* ROOM & BOARD ------------------------------------------------------------------------------------------------*/
/* 7-15-03 combined room and board fees and separated into quarters*/

/* fall housing & meal rates, order: dblroom; snglroom; superSingle; triple; birnamWood BWdbl, BWsngl, BWfamily*/
/* mealplan order: ul, 125, 100, 75 */
temp_string = "0,4332,4169,4009,3844,4776,4613,4453,4288,5033,4870,4710,4545,3791,3628,3468,3303,1206,2407,4821";
froomArray = temp_string.split(',');

/* winter housing & meal rates, same order as above*/
temp_string = "0,3762,3621,3481,3338,4147,4006,3866,3723,4371,4230,4090,3947,3292,3151,3011,2866,1206,2407,4821";
wroomArray = temp_string.split(',');

/* spring housing & meal rates, same order as above*/
temp_string = "0,3306,3181,3059,2933,3645,3520,3398,3272,3841,3716,3594,3468,2893,2768,2646,2520,1206,2407,4821";
sroomArray = temp_string.split(',');
/*--------------------------------------------------------------------------------------------------------------*/

/* variable fees */
/* Course fees */
temp_string = "0,10,25,50,75,100,125,150,175,200,225,250,275,300,350,400,500";
courseArray = temp_string.split(',');

/* Books */
temp_string = "0,100,150,200,250,300,350,400,450,500,550,600,650,700,750";
bookArray = temp_string.split(',');

/* Transportation */
temp_string = "0,250,500,750,1000,1250,1500,1750,2000,2500,3000,3500,4000";
transArray = temp_string.split(',');

/* Misc Costs */
temp_string = "0,100,250,500,750,1000,1250,1500,1750,2000";
miscArray = temp_string.split(',');

/* Parking: Campus resident, C commuter, CR resident, Carpool, motorcycle */
temp_string = "0,102,90,90,81,19";
parkArray = temp_string.split(',');

function rplanCheck(form) {
	rplanflag = 1;
	if ((rcheck>0) && (rcheck<5) && ((mcheck==0) || (mcheck >7)))
		rplanflag = 0;
	return(rplanflag);
}

function ocplanCheck(form) {
	ocplanflag = 1;
	if (((rcheck==0) || (rcheck>4)) && ((mcheck>0) && (mcheck < 7)))
		ocplanflag = 0;
	return(ocplanflag);
}

function calcTotal(form) {
	var tempArray = new Array();
	var trate = 0;
	var TOTtuition = 0;
	var TOTcredits = 0;
	var froom = 0;
	var wroom = 0;
	var sroom = 0;
	var TOTroom = 0;
	var TOTfeef = 0;
	var TOTfeew = 0;
	var TOTfees = 0;
	var TOTsafeef = 0;
	var TOTsafeew = 0;
	var TOTsafees = 0;
	var TOTfeeyr = 0;
	var yrcredit = 0;
	var newstudent = 0;
	var TOTtuitf = 0;
	var TOTtuitw = 0;
	var TOTtuits = 0;
	var rbf = 0;
	var rbw = 0;
	var rbs = 0;
	var TOTqtrf = 0;
	var TOTqtrw = 0;
	var TOTqtrs = 0;

/* health service fee variables */
	var FEEhs = 0;
	var FEEhsw = 0;
	var FEEhss = 0;
/* legislative action fee variables */
	var FEEla = 0;
	var FEElaw = 0;
	var FEElas = 0;
/* technology fee variables */
	var FEEt = 0;
	var FEEtw = 0;
	var FEEts = 0;
/* non-academic building fee variables */
	var FEEnab = 0;
	var FEEnabw = 0;
	var FEEnabs = 0;
/* rec center fee variables */
	var FEErec = 0;
	var FEErecw = 0;
	var FEErecs = 0;
/* renewable energy fee variables */
	var FEEref = 0;
	var FEErefw = 0;
	var FEErefs = 0;
/* transportation fee variables */
	var FEEtrfee = 0;
	var FEEtrfeew = 0;
	var FEEtrfees = 0;
/* course fees variables */
	var FEEcrs = 0;
	var FEEcrsw = 0;
	var FEEcrss = 0;
/* service & activity fees variables */
	var FEEsaf = 0;
	var FEEsaw = 0;
	var FEEsas = 0;

/* orientation fee variables */
	var FEEor = 0;

/* book cost variables */
	var FEEbook = 0;
	var FEEbookw = 0;
	var FEEbooks = 0;

/* transportation cost variables */
	var FEEtransp = 0;
	var FEEtranspw = 0;
	var FEEtransps = 0;

/* parking cost variables */
	var FEEpkg = 0;
	var FEEpkgw = 0;
	var FEEpkgs = 0;

/* misc cost variables */
	var FEEmisc = 0;
	var FEEmiscw = 0;
	var FEEmiscs = 0;

/* load number of credit */
	creditArray[0]=form.fallqtr.selectedIndex;
	creditArray[1]=form.wtrqtr.selectedIndex;
	creditArray[2]=form.sprqtr.selectedIndex;

/* check resident status and undergrad or grad */
	if (form.resident.selectedIndex==0) {
		tempArray=WAArray;
		if (form.ugradgrad.selectedIndex==0) {
			trate=0;
		}
		else {
			trate=2;
		}
	}
	else {
		tempArray=NONArray;
		if (form.ugradgrad.selectedIndex==0) {
			trate=1;
		}
		else {
			trate=3;
		}
	}

/* set variables for grad or undergrad < 10 credits or > 18 credits*/
	if (form.ugradgrad.selectedIndex==0) {
		ugt=0;
		uge=1;
	}
	else {
		ugt=2;
		uge=3;
	}

/* calculate tuition = number of credits * credit fee*/
	for (i=0;i<3;i++){
		cr = parseInt(creditArray[i]);
		if (cr < 10) {
			tuition = cr * parseInt(tempArray[ugt]);
			TOTtuition += cr * parseInt(tempArray[ugt]);
		}
		else {
			if (cr <= 18) {
				tuition = parseInt(t1018Array[trate]);
				TOTtuition += parseInt(t1018Array[trate]);
			}
			else {
				tuition = (parseInt(t1018Array[trate]) + ((cr-18) * parseInt(tempArray[uge])));
				TOTtuition += (parseInt(t1018Array[trate]) + ((cr-18) * parseInt(tempArray[uge])));
			}
		} /* end if */
		qtrArray[i] = tuition;
		TOTtuitf = parseInt(qtrArray[0]);
		TOTtuitw = parseInt(qtrArray[1]);
		TOTtuits = parseInt(qtrArray[2]);
	} /* end for */

/* calculate fees */
		crf = parseInt(creditArray[0]);
		crw = parseInt(creditArray[1]);
		crs = parseInt(creditArray[2]);

		if (crf > 5) {
			FEEhs = 106;
			FEEla = 1;
   			FEEt = 35;
			FEEnab = 45;
			FEErec = 101.20;
			FEEtrfee = 26.25;
		}
		else
		{
			FEEt = 17.50;
			FEEla = 1;
		}
		if (crw > 5) {
			FEEhsw = 106;
			FEElaw = 1;
   			FEEtw = 35;
			FEEnabw = 45;
			FEErecw = 101.20;
			FEEtrfeew = 26.25;
		}
		else
		{
			FEEtw = 17.50;
			FEElaw = 1;
		}
		if (crs > 5) {
			FEEhss = 106;
			FEElas = 1;
   			FEEts = 35;
			FEEnabs = 45;
			FEErecs = 101.20;
			FEEtrfees = 26.25;
		}
		else
		{
			FEEts = 17.50;
			FEElas = 1;
		}

/* calculate sustainable action fund(fee) as $.70 * credit hour, maximum of $7.00 */
        FEEref = (crf * .70);
	    if (FEEref > 7.00)
		{
			FEEref = 7.00;
		}
		else
		{
			Math.round(FEEref * Math.pow(10,2))/Math.pow(10,2)
		}

		FEErefw = crw * .70;
	    if (FEErefw > 7.00)
		{
			FEErefw = 7.00;
		}
		else
		{
			Math.round(FEErefw * Math.pow(10,2))/Math.pow(10,2)
		}

		FEErefs = crs * .70;
	    if (FEErefs > 7.00)
		{
			FEErefs = 7.00;
		}
		else
		{
			Math.round(FEErefs * Math.pow(10,2))/Math.pow(10,2)
		}

/* calculate service & activity fees - $19.70 per credit, max amount $197.00 */
        FEEsaf = (crf * 20.95);
	    if (FEEsaf > 209.50)
		{
			FEEsaf = 209.50;
		}
		else
		{
			Math.round(FEEsaf * Math.pow(10,2))/Math.pow(10,2)
		}

		FEEsaw = crw * 20.95;
	    if (FEEsaw > 209.50)
		{
			FEEsaw = 209.50;
		}
		else
		{
			Math.round(FEEsaw * Math.pow(10,2))/Math.pow(10,2)
		}

		FEEsas = crs * 20.95;
	    if (FEEsas > 209.50)
		{
			FEEsas = 209.50;
		}
		else
		{
			Math.round(FEEsas * Math.pow(10,2))/Math.pow(10,2)
		}
/* first time enrollee's are charged a one-time student orientation fee fall quarter*/

	if ((form.newstudent.selectedIndex==0) && (form.ugradgrad.selectedIndex==0)){
		FEEor = 280;
	}
	else
		FEEor=0;

	yrcredit  = form.fallqtr.selectedIndex + form.wtrqtr.selectedIndex + form.sprqtr.selectedIndex;

/* Room and boards data */
	froom = parseInt(froomArray[form.roomf.selectedIndex]);
	wroom = parseInt(wroomArray[form.roomw.selectedIndex]);
	sroom = parseInt(sroomArray[form.rooms.selectedIndex]);
	TOTroom = froom + wroom + sroom;

/* Course Fee data */
	FEEcrs = parseInt(courseArray[form.course.selectedIndex]);
	FEEcrsw = parseInt(courseArray[form.coursew.selectedIndex]);
	FEEcrss = parseInt(courseArray[form.courses.selectedIndex]);

/* Book Expense data */
	FEEbook = parseInt(bookArray[form.books.selectedIndex]);
	FEEbookw = parseInt(bookArray[form.booksw.selectedIndex]);
	FEEbooks = parseInt(bookArray[form.bookss.selectedIndex]);

/* Book Expense data */
	FEEtransp = parseInt(transArray[form.trans.selectedIndex]);
	FEEtranspw = parseInt(transArray[form.transw.selectedIndex]);
	FEEtransps = parseInt(transArray[form.transs.selectedIndex]);

/* Miscellaneous Expense data */
	FEEmisc = parseInt(miscArray[form.misc.selectedIndex]);
	FEEmiscw = parseInt(miscArray[form.miscw.selectedIndex]);
	FEEmiscs = parseInt(miscArray[form.miscs.selectedIndex]);

/* Parking Expense data */
	FEEpkg = parseFloat(parkArray[form.parking.selectedIndex]);
	FEEpkgw = parseFloat(parkArray[form.parkingw.selectedIndex]);
	FEEpkgs = parseFloat(parkArray[form.parkings.selectedIndex]);

	TOTtuition = TOTtuition + FEEsaf + FEEsaw + FEEsas;

	TOTfeef = FEEhs + FEEla + FEEt + FEEnab + FEErec + FEEtrfee + FEEref + FEEor + FEEcrs + FEEbook + FEEtransp + FEEmisc + FEEpkg;
	TOTfeew = FEEhsw + FEElaw + FEEtw + FEEnabw + FEErecw + FEEtrfeew + FEErefw + FEEcrsw + FEEbookw + FEEtranspw + FEEmiscw + FEEpkgw;
	TOTfees = FEEhss + FEElas + FEEts + FEEnabs + FEErecs + FEEtrfees + FEErefs + FEEcrss + FEEbooks + FEEtransps + FEEmiscs + FEEpkgs;
	TOTfeeyr = TOTfeef + TOTfeew + TOTfees;
	TOTmandf = FEEhs + FEEla + FEEt + FEEnab + FEErec + FEEtrfee + FEEref + FEEor + FEEcrs;
	TOTmandw = FEEhsw + FEElaw + FEEtw + FEEnabw + FEErecw + FEEtrfeew + FEErefw + FEEcrsw;
	TOTmands = FEEhss + FEElas + FEEts + FEEnabs + FEErecs + FEEtrfees + FEErefs + FEEcrss;

	form.TOTcredits.value = yrcredit;
	form.TOTtuitf.value = TOTtuitf.toFixed(2);
	form.TOTtuitw.value = TOTtuitw.toFixed(2);
	form.TOTtuits.value = TOTtuits.toFixed(2);
	form.FEEsaf.value = FEEsaf.toFixed(2);
	form.FEEsaw.value = FEEsaw.toFixed(2);
	form.FEEsas.value = FEEsas.toFixed(2);
	form.TOTtuit.value = TOTtuition.toFixed(2);
 	form.rbf.value = froom.toFixed(2);
	form.rbw.value = wroom.toFixed(2);
	form.rbs.value = sroom.toFixed(2);
	form.TOTroom.value = TOTroom.toFixed(2);
	form.FEEhs.value = FEEhs.toFixed(2);
	form.FEEhsw.value = FEEhsw.toFixed(2);
	form.FEEhss.value = FEEhss.toFixed(2);
	form.FEEla.value = FEEla.toFixed(2);
	form.FEElaw.value = FEElaw.toFixed(2);
	form.FEElas.value = FEElas.toFixed(2);
	form.FEEt.value = FEEt.toFixed(2);
	form.FEEtw.value = FEEtw.toFixed(2);
	form.FEEts.value = FEEts.toFixed(2);
	form.FEEnab.value = FEEnab.toFixed(2);
	form.FEEnabw.value = FEEnabw.toFixed(2);
	form.FEEnabs.value = FEEnabs.toFixed(2);
	form.FEErec.value = FEErec.toFixed(2);
	form.FEErecw.value = FEErecw.toFixed(2);
	form.FEErecs.value = FEErecs.toFixed(2);
	form.FEEtrfee.value = FEEtrfee.toFixed(2);
	form.FEEtrfeew.value = FEEtrfeew.toFixed(2);
	form.FEEtrfees.value = FEEtrfees.toFixed(2);
	form.FEEref.value = FEEref.toFixed(2);
	form.FEErefw.value = FEErefw.toFixed(2);
	form.FEErefs.value = FEErefs.toFixed(2);
	form.FEEor.value = FEEor.toFixed(2);

/*	form.FEEpkg.value = FEEpkg */

	form.TOTfees.value = TOTfeef.toFixed(2);
	form.TOTfeesw.value = TOTfeew.toFixed(2);
	form.TOTfeess.value = TOTfees.toFixed(2);
	form.TOTfeeyr.value = TOTfeeyr.toFixed(2);

	FallTotal = TOTfeef + froom + TOTtuitf + FEEsaf;
	WtrTotal = TOTfeew + wroom + TOTtuitw + FEEsaw;
	SprTotal = TOTfees + sroom + TOTtuits + FEEsas;
	FallDue = TOTmandf + froom + TOTtuitf + FEEsaf;
	WtrDue = TOTmandw + wroom + TOTtuitw + FEEsaw;
	SprDue = TOTmands + sroom + TOTtuits + FEEsas;
	wwutotal = TOTtuition + TOTroom + TOTfeeyr;

	form.FallTotal.value = FallTotal.toFixed(2);
	form.WtrTotal.value = WtrTotal.toFixed(2);
	form.SprTotal.value = SprTotal.toFixed(2);

	form.FallDue.value = FallDue.toFixed(2);
	form.WtrDue.value = WtrDue.toFixed(2);
	form.SprDue.value = SprDue.toFixed(2);

	form.wwutotal.value = wwutotal.toFixed(2);

	return;
}

</script>
