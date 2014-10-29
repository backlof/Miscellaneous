function goBack(path)
{
    window.location = path;
}
function timeout(path, time)
{
	var timeout = setTimeout(function(){goBack(path)}, time);
}