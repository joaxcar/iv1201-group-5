function postToAPI(
	endpoint,
	body,
	headers = { "Content-Type": "application/json" }
) {
	return fetch(endpoint, {
		method: "POST",
		headers,
		body: JSON.stringify(body),
	}).then((response) => response.json());
}

export { postToAPI };
