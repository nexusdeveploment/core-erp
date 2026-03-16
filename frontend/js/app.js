async function loadComponent(elementId, path) {
  const res = await fetch(path);
  const html = await res.text();
  document.getElementById(elementId).innerHTML = html;
}

async function loadModule(moduleName) {
  const appView = document.getElementById("app-view");

  const htmlRes = await fetch(`./modules/${moduleName}/${moduleName}.html`);
  const html = await htmlRes.text();
  appView.innerHTML = html;

  const oldScript = document.getElementById("dynamic-module-script");
  if (oldScript) oldScript.remove();

  const script = document.createElement("script");
  script.src = `./modules/${moduleName}/${moduleName}.js?v=${Date.now()}`;
  script.id = "dynamic-module-script";
  document.body.appendChild(script);

  ativarLinkSidebar(moduleName);
}

async function initApp() {
  await loadComponent("sidebar", "./components/sidebar.html");
  await loadComponent("topbar", "./components/topbar.html");

  const rota = window.location.hash.replace("#", "") || "dashboard";
  await loadModule(rota);
}

window.addEventListener("hashchange", async () => {
  const rota = window.location.hash.replace("#", "") || "dashboard";
  await loadModule(rota);
});

initApp();