<script language="javascript" type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
 <script  language="javascript" type="text/javascript"  src="../js/jquery.min.js"></script> 


<form name="para_form" action="./NLPTest" target="_balnk">
<label for="para"> Enter your text :</label>

<input type="text" name="input" id="input" class="p" />
<input type="submit" value="Submit" class="submitbutton" />

</form>

<%if(request.getParameter("input") !=null){ %>
<br>
<p>Nummber of Sentences : <%=request.getAttribute("noOfSentences")  %></p>
<p>Number of Words : <%=request.getAttribute("noofwords") %></p>
<p>Names : <%=request.getAttribute("names") %></p>
<p>Location Names : <%= request.getAttribute("locationnames") %></p>
<p>Organization Names : <%= request.getAttribute("orgnames")  %></p>
<p>Money Names : <%= request.getAttribute("moneynames")  %></p>
<%} %>