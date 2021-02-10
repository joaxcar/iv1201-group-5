function handleResponse(response) {
	if (![200, 201].includes(response.status)) {
		throw new Error("Something went wrong");
	} else {
		return response.json();
	}
}

function postToAPI(
	endpoint,
	body,
	headers = { "Content-Type": "application/json" }
) {
	return fetch(endpoint, {
		method: "POST",
		headers,
		body: JSON.stringify(body),
	}).then(handleResponse);
}

export { postToAPI };
