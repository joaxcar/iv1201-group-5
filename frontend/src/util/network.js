function handleResponse(response) {
	if (![200, 201].includes(response.status)) {
		throw new Error("Something went wrong");
	} else {
		return response.json();
	}
}

/**
 * A function that makes a POST request to the API.
 * If no header object is supplied, it will default to
 * Content-Type: application/json
 * @param {endpoint} endpoint - The endpoint to call
 * @param {object} body - The body to post
 * @param {object} headers - Object of key value headers to use for the request
 */
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
